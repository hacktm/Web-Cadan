application.service('Session', ['RestService', '$cookieStore', '$rootScope', function(RestService, $cookieStore, $rootScope) {
    var session = {
        'username': 'cristi',
        'email': 'accexpert@gmail.com',
        'session_id': '',
        'name': 'Cristi',
        'description': 'Some description',
        'is_owner': false,
        'owner_name': ''
    };
    this.setSession = function(sessionData) {
        session = sessionData;
        $cookieStore.put("meeting-notes-session", session);
    };
    this.getSession = function() {
        coockieInfo = $cookieStore.get("meeting-notes-session");
        if (coockieInfo == undefined) {
            return session;
        } else {
            session = coockieInfo;
            return session;
        }
    };
    this.deleteSession = function() {
        $cookieStore.remove("meeting-notes-session");
        session = {};
    }
    this.sessionStarted = function() {
        coockieInfo = $cookieStore.get("meeting-notes-session");
        if (coockieInfo == undefined) {
            return false;
        } else {
            session = coockieInfo;
            return true;
        }
    }
    return this;
}]);