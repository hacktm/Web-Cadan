application.controller('FrontpageController', ['$scope', '$rootScope', 'WebsocketService', 'RestService', 'Session',
    function($scope, $rootScope, WebsocketService, RestService, Session){
        var lang = {"page_title": "Live Notes"};
        $rootScope.lang = lang;
        $scope.session = Session.getSession();
        $scope.userHasSession = Session.sessionStarted();
        $scope.meetingNotesEditable = false;
        $scope.noteText = "&nbsp;";
        $scope.timeElapsed = "";
        $scope.showStartMeetingPopup = false; //!$scope.userHasSession;
        var subscribeToServerFeed = function(sessionId){
            WebsocketService.subscribe(sessionId, function(message) {
                var msgJson = JSON.parse(message);
                //update meeting note
                if (msgJson.message_type == 0) {
                    var msg = msgJson.data;
                    if (msgJson.email == Session.getSession().email) {
                        return;
                    }
                    if ($scope.meetingNotesEditable) {
                        $scope.$apply(function(){
                            $scope.noteText = msg;
                        });
                    } else {
                        $scope.$apply(function(){
                            $scope.meetingNotes = msgJson.notes;
                        });
                    }

                }
                //new user joined the meeting
                if (msgJson.message_type == 3) {
                    $scope.$apply(function(){
                        $scope.connectedUsers = msgJson.users;
                    });
                }
                //user left the meeting
                if (msgJson.message_type == 1) {
                    if (msgJson.email != Session.getSession().email) {
                        $scope.$apply(function(){
                            $scope.connectedUsers = msgJson.users;
                        });
                    } else {
                        $scope.$apply(function(){
                            $scope.connectedUsers = msgJson.users;
                        });
                    }
                }
                //owner close the meeting
                if (msgJson.message_type == 2) {
                    if (msgJson.email != Session.getSession().email) {
                        $scope.$apply(function(){
                            $scope.connectedUsers = msgJson.users;
                        });
                        Session.deleteSession();
                        $scope.userHasSession = Session.sessionStarted();
                        $scope.meetingNotesEditable = false;
                        getSessionList();
                        WebsocketService.disconnect();
                        $scope.$apply(function(){
                            $scope.noteText = "";
                        });
                        $scope.$apply(function(){
                            $scope.meetingNotes = {};
                        });

                    }
                }
                if (msgJson.message_type == 4) {
                    $scope.$apply(function(){
                        $scope.timeElapsed = msgJson.time;
                    });
                    $scope.$apply(function(){
                        $scope.startDate = msgJson.date;
                    });
                }

            });
        };
        var getSessionList = function(){
            RestService.getSessionsList($scope.session.name, $scope.session.email).then(
                function(response){
                $scope.sessionsList = response.active_sessions;
                if (response.active_sessions.length>0) {
                    $scope.sessionsListValue = response.active_sessions[0];
                } else {
                    $scope.sessionsListValue = null;
                }
            },
            function(error){

            });
        };
        var checkSession = function(name, email, sessionId){
            if (Session.sessionStarted()) {
                RestService.checkClientSession(name, email, sessionId).then(
                    function(response){
                        var session = {description: response.description, is_owner: response.is_owner, name: response.name, username: response.username, session_id: response.session_id, email: response.email, owner_name: response.owner_name};
                        Session.setSession(session);
                        $scope.meetingNotesEditable = session.is_owner;
                        if (!response.status.status) {
                            Session.deleteSession();
                            $scope.showStartMeetingPopup = false;
                        } else {
                            $scope.noteText = session.notes;
                            $scope.connectedUsers = response.users;
                            subscribeToServerFeed(session.session_id);
                            $scope.showStartMeetingPopup = false;
                        }
                        $scope.userHasSession = Session.sessionStarted();
                    },
                    function(error){

                    });
                }
            };
        checkSession(Session.getSession().name, Session.getSession().email, Session.getSession().session_id)
        getSessionList();
        $scope.endSession = function() {
            RestService.endClientSession(Session.getSession().session_id, Session.getSession().name, Session.getSession().email).then(
                function(response){
                    Session.deleteSession();
                    $scope.userHasSession = Session.sessionStarted();
                    $scope.meetingNotesEditable = false;
                    getSessionList();
                    WebsocketService.disconnect();
                    $scope.connectedUsers = response.users;
                    $scope.noteText = "";
                },
                function(error){

            });
        }
        $scope.joinSession = function(sessionId) {
            RestService.joinClientSession(sessionId, $scope.session.name, $scope.session.email).then(
                function(response) {
                    if(!response.status.status) {
                        return;
                    }
                    var session = {description: response.description, name: response.name, username: response.username, session_id: response.session_id, email: response.email, owner_name: response.owner_name};
                    $scope.meetingNotesEditable = session.is_owner;
                    Session.setSession(session);
                    $scope.userHasSession = Session.sessionStarted();
                    subscribeToServerFeed(session.session_id);
                    $scope.meetingNotesEditable = false;
                    $scope.noteText = session.notes;
                    $scope.connectedUsers = response.users;
                    $scope.showStartMeetingPopup = false;
                },
                function(error) {
                    $scope.showStartMeetingPopup = false;
                }
            );
        };
        $scope.startNewSession = function() {
            if (!Session.sessionStarted()) {
                RestService.newClientSession($scope.session.name, $scope.session.email, $scope.session.description).then(
                    function(response){
                        if (response.status) {
                            var session = {is_owner:response.is_owner, description: response.description, name: response.name, username: response.username, session_id: response.session_id, email: response.email, owner_name: response.owner_name};
                            $scope.meetingNotesEditable = session.is_owner;
                            Session.setSession(session);
                            $scope.userHasSession = Session.sessionStarted();
                            subscribeToServerFeed(session.session_id);
                            $scope.showStartMeetingPopup = false;
                        } else {
                            $scope.showStartMeetingPopup = false;
                        }
                    },
                    function(error) {
                        $scope.showStartMeetingPopup = false;
                    }
                );
            } else {

            }
        }

        $scope.viewHistory = function() {
            if (!Session.sessionStarted()) {
                RestService.getUserHistory($scope.session.email).then(
                function(response){
                    $scope.historyList = response.ended_sessions; //sessionsList
                    if (response.ended_sessions.length>0) {
                        $scope.historyList = response.ended_sessions;
                    } else {
                        $scope.historyList = null;
                    }
                },
                function(error){

                });
            }
        }

        $scope.start = function() {
            RestService.newClientSession()
                .then(function(data){
                    $scope.retValue=data.status;
                    if (WebsocketService.isOpen()) {

                    } else {
                        subscribeToServerFeed(session.session_id);
                    }
                },
                function(){

                });
            }
        $scope.$watch('noteText', function(value){
            if ($scope.meetingNotesEditable) {
                var msg = {'message': value, is_private: false, message_type:0};
                WebsocketService.send(JSON.stringify(msg), Session.getSession().session_id, Session.getSession().username);
            }
        });

        $scope.addComment = function(noteId) {
            var comment = prompt("Please enter a comment");
            if (comment) {
                var msg = {email: Session.getSession().email, name:Session.getSession().name,row_id:noteId, data:comment};
                WebsocketService.sendCommand(JSON.stringify(msg), Session.getSession().session_id, 0);
            }
        }




        $scope.notesData = {};



    }])
    .controller('EntryController',['$scope', '$location', function($scope, $location){
        $scope.go = function(path) {
            $location.path(path);
        }
    }])
.config(['$routeProvider', '$locationProvider',
    function($routeProvider, $locationProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'pages/entry.html',
                controller: 'EntryController',
                resolve: {
                }
            })
            .when('/meetings', {
                templateUrl: 'pages/frontpage.html',
                controller: 'FrontpageController',
                resolve: {
                }
            })
            .otherwise({
                redirectTo: '/'
            });
    }
])
.directive('notesArea', function(){
    return {
        restrict: 'EA',
        replace: true,
        require: "ngModel",
        templateUrl: 'pages/notes_area.html',
        link: function(scope, element, attrs, ngModel) {
            if (!ngModel) return;
            function read() {
                ngModel.$setViewValue(element.html());
            }
            ngModel.$render = function() {
                element.html(ngModel.$viewValue || "");
            };
            element.bind("blur keyup change", function(event) {
//                if (scope.meetingNotesEditable) {
                  scope.$apply(read);
//                }
            });
        }
    };
})
.directive('readOnlyNotesArea', function(){
    return {
        restrict: 'EA',
        replace: true,
        templateUrl: 'pages/read_only_notes_area.html',
        controller: function() {

        }
    };
})
.directive('startMeetingPopup', function(){
    return {
        restrict: 'EA',
        replace: true,
        templateUrl: 'pages/start_meeting.html',
        controller: function($scope) {
            $scope.closePopup = function() {

            }
        }
    };
});