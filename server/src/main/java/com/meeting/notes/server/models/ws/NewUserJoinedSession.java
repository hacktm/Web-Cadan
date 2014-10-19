package com.meeting.notes.server.models.ws;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meeting.notes.server.models.transport.User;

import java.util.List;

public class NewUserJoinedSession extends BaseWsModel{
    @JsonProperty(value = "session_id")
    private String sessionId;
    @JsonProperty(value = "users")
    private List<User> user;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "NewUserJoinedSession{" +
                "sessionId='" + sessionId + '\'' +
                ", user=" + user +
                '}';
    }
}
