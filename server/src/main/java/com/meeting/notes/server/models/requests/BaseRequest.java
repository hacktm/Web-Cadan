package com.meeting.notes.server.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseRequest {
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "email")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "BaseRequest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
