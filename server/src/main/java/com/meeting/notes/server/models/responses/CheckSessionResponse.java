package com.meeting.notes.server.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meeting.notes.server.models.transport.Status;
import com.meeting.notes.server.models.transport.User;

import java.util.List;

public class CheckSessionResponse extends BaseResponse{
    @JsonProperty(value = "status")
    private Status status;
    @JsonProperty(value = "session_id")
    private String sessionId;
    @JsonProperty(value = "username")
    private String userName;
    @JsonProperty(value = "description")
    private String description;
    @JsonProperty(value = "users")
    private List<User> users;
    @JsonProperty(value = "owner_name")
    private String ownerName;

    public CheckSessionResponse() {
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Override
    public String toString() {
        return "CheckSessionResponse{" +
                "status=" + status +
                ", sessionId='" + sessionId + '\'' +
                ", userName='" + userName + '\'' +
                ", description='" + description + '\'' +
                ", users=" + users +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }
}
