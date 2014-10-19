package com.meeting.notes.server.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meeting.notes.server.models.transport.Status;

public class CreateUserSessionResponse extends BaseResponse{
    @JsonProperty(value = "session_id")
    private String sessionId;
    @JsonProperty(value = "status")
    private Status status;
    @JsonProperty(value = "username")
    private String username;
    @JsonProperty(value = "description")
    private String description;
    @JsonProperty(value = "owner_name")
    private String ownerName;


    public CreateUserSessionResponse() {
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Override
    public String toString() {
        return "CreateUserSessionResponse{" +
                "sessionId='" + sessionId + '\'' +
                ", status=" + status +
                ", username='" + username + '\'' +
                ", description='" + description + '\'' +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }
}
