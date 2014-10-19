package com.meeting.notes.server.models.ws;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meeting.notes.server.models.transport.User;

import java.util.List;

public class UserLeftSession extends BaseWsModel{
    @JsonProperty(value = "session_id")
    private String sessionId;
    @JsonProperty(value = "email")
    private String email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserLeftSession{" +
                "sessionId='" + sessionId + '\'' +
                ", email='" + email + '\'' +
                ", user=" + user +
                '}';
    }
}
