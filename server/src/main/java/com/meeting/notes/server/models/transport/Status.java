package com.meeting.notes.server.models.transport;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Status {
    @JsonProperty(value = "status")
    private Boolean status;
    @JsonProperty(value = "message")
    private String message;

    public Status() {
    }

    public Status(Boolean status) {
        this.status = status;
    }

    public Status(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Status{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
