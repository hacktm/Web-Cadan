application.factory('WebsocketService', ['$q', '$rootScope', function ($q, $rootScope) {
    var WebsocketService = {};
    $rootScope.serverLinkIsOpen;
    var stompClient;
    var callbackNotificationFromServer;
    var disconectAtRequest = false;
    WebsocketService.subscribe = function (sessionId, callbackNotificationFromServer) {
        var client = new SockJS("/server-websocket");
        stompClient = Stomp.over(client);
        disconectAtRequest = false;
        stompClient.connect({},
            function () {
                stompClient.subscribe("/topic/"+sessionId, function (message) {
                    callbackNotificationFromServer(message.body);
                });
                $rootScope.serverLinkIsOpen = true;
            });
        client.onclose = function () {
            $rootScope.serverLinkIsOpen = false;
            if (!disconectAtRequest) {
                setTimeout(WebsocketService.init, 2000);
            }
        };
    };
    WebsocketService.isOpen = function () {
        return $rootScope.serverLinkIsOpen;
    };
    WebsocketService.send = function (message, sessionId, username) {
        if ($rootScope.serverLinkIsOpen) {
            stompClient.send("/app/server-websocket/"+sessionId+"/"+username, {}, message);
        }
    };
    WebsocketService.sendCommand = function (message, sessionId, username, command) {
        if ($rootScope.serverLinkIsOpen) {
            stompClient.send("/app/server-websocket/"+sessionId+"/"+username+"/"+command, {}, message);
        }
    };
    WebsocketService.disconnect = function() {
        disconectAtRequest = true;
        stompClient.disconnect();
    }
    return WebsocketService;
}]);

application.factory('RestService', ['$http', '$q', function ($http, $q) {
    var restService = {};
    restService.newClientSession = function (varName, varEmail, varDescription) {
        var defer = $q.defer();
        var data = {name: varName, email: varEmail, description: varDescription};
        $http.post('/new.session', data)
            .success(function (response, status) {
                defer.resolve(response);
            });
        return defer.promise;
    };
    restService.getUserHistory = function(varEmail) {
        var defer = $q.defer();
        var data = {email: varEmail};
        $http.post('/get.ended.sessions', data)
            .success(function (response, status) {
                defer.resolve(response);
            });
        return defer.promise;
    }
    restService.joinClientSession = function (varSessionId, varName, varEmail) {
        var defer = $q.defer();
        var data = {session_id: varSessionId, name: varName, email: varEmail};
        $http.post('/join.session', data)
            .success(function (response, status) {
                defer.resolve(response);
            });
        return defer.promise;
    };
    restService.checkClientSession = function (varName, varEmail, varSessionId) {
        var defer = $q.defer();
        var data = {name: varName, email: varEmail, session_id: varSessionId};
        $http.post('/check.session', data)
            .success(function (response, status) {
                defer.resolve(response);
            });
        return defer.promise;
    };
    restService.endClientSession = function (varSessionId, varName, varEmail) {
        var defer = $q.defer();
        var data = {session_id: varSessionId, name: varName, email: varEmail};
        $http.post('/end.session', data)
            .success(function (response, status) {
                defer.resolve(response);
            });
        return defer.promise;
    };
    restService.getSessionsList = function (varName, varEmail) {
        var defer = $q.defer();
        var data = {name: varName, email: varEmail};
        $http.post('/get.all.active.sessions', data)
            .success(function (response, status) {
                defer.resolve(response);
            });
        return defer.promise;
    };
    return restService;
}]);
