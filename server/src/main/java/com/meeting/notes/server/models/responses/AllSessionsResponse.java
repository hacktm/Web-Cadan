package com.meeting.notes.server.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meeting.notes.server.models.ClientSessionModel;

import java.util.ArrayList;
import java.util.List;

public class AllSessionsResponse extends BaseResponse{
    @JsonProperty(value = "active_sessions")
    private List<ClientSessionModel> activeSessions;

    public List<ClientSessionModel> getActiveSessions() {
        if(activeSessions == null) {
            return new ArrayList<>();
        }
        return activeSessions;
    }

    public void setActiveSessions(List<ClientSessionModel> activeSessions) {
        this.activeSessions = activeSessions;
    }

    @Override
    public String toString() {
        return "EndedSessionsResponse{" +
                "activeSessions=" + activeSessions +
                '}';
    }
}
