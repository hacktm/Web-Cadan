package com.meeting.notes.server.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CheckSessionRequest extends BaseRequest{
    @JsonProperty(value = "session_id")
    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "JoinSessionRequest{" +
                "sessionId='" + sessionId + '\'' +
                '}';
    }
}
