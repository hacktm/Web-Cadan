package com.meeting.notes.server.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateUserSessionRequest extends BaseRequest{

    public CreateUserSessionRequest() {
        super();
    }

    @JsonProperty(value = "description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CreateUserSessionRequest{" +
                "description='" + description + '\'' +
                '}';
    }
}
