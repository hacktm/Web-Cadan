package com.meeting.notes.server.handlers;

import com.meeting.notes.server.models.ClientSessionModel;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ClientsHandler {
    private Map<String, ClientSessionModel> sessionMap;

    public ClientsHandler() {
        sessionMap = new ConcurrentHashMap<>();
    }

    public Map<String, ClientSessionModel> getSessionMap() {
        return sessionMap;
    }

    public void setSessionMap(Map<String, ClientSessionModel> sessionMap) {
        this.sessionMap = sessionMap;
    }

    public boolean addSession(String sessionId, ClientSessionModel sessionModel) {
        if (sessionMap.containsKey(sessionId)) {
            return false;
        }
        sessionMap.put(sessionId, sessionModel);
        return true;
    }

    public ClientSessionModel getSession(String sessionId) {
        if (sessionMap.containsKey(sessionId)) {
            return sessionMap.get(sessionId);
        }
        return null;
    }

    public boolean userHasActiveMeeting(String email) {
        for (Map.Entry<String, ClientSessionModel> value: sessionMap.entrySet()) {
            if (value.getValue().getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    public ClientSessionModel getSessionByOwner(String email) {
        for (Map.Entry<String, ClientSessionModel> value: sessionMap.entrySet()) {
            if (value.getValue().getEmail().equalsIgnoreCase(email)) {
                return value.getValue();
            }
        }
        return null;
    }

}