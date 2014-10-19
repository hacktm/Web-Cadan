package com.meeting.notes.server.models.ws;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OwnerEndSession extends BaseWsModel{
    @JsonProperty(value = "session_id")
    private String sessionId;
    @JsonProperty(value = "email")
    private String email;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "OwnerEndSession{" +
                "sessionId='" + sessionId + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
