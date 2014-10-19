package com.meeting.notes.server.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meeting.notes.server.models.transport.Status;
import com.meeting.notes.server.models.transport.User;

import java.util.List;

public class JoinSessionResponse extends BaseResponse{
    @JsonProperty(value = "status")
    private Status status;
    @JsonProperty(value = "session_id")
    private String sessionId;
    @JsonProperty(value = "username")
    private String userName;
    @JsonProperty(value = "description")
    private String description;
    @JsonProperty(value = "notes")
    private String notes;
    @JsonProperty(value = "users")
    private List<User> users;
    @JsonProperty(value = "owner_name")
    private String ownerName;

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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
        return "JoinSessionResponse{" +
                "status=" + status +
                ", sessionId='" + sessionId + '\'' +
                ", userName='" + userName + '\'' +
                ", description='" + description + '\'' +
                ", notes='" + notes + '\'' +
                ", users=" + users +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }
}
