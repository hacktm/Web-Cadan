package com.meeting.notes.server.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meeting.notes.server.models.transport.Status;

public class AddNoteForUserResponse extends BaseResponse{
    @JsonProperty(value = "status")
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AddNoteForUserResponse{" +
                "status=" + status +
                '}';
    }
}
