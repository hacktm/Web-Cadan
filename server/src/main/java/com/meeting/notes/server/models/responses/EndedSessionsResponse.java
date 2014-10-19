package com.meeting.notes.server.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meeting.notes.server.models.ClientSessionModel;

import java.util.ArrayList;
import java.util.List;

public class EndedSessionsResponse extends BaseResponse{
    @JsonProperty(value = "ended_sessions")
    private List<ClientSessionModel> endedSessions;

    public List<ClientSessionModel> getEndedSessions() {
        if(endedSessions == null) {
            return new ArrayList<>();
        }
        return endedSessions;
    }

    public void setEndedSessions(List<ClientSessionModel> endedSessions) {
        this.endedSessions = endedSessions;
    }

    @Override
    public String toString() {
        return "EndedSessionsResponse{" +
                "endedSessions=" + endedSessions +
                '}';
    }
}
