package com.meeting.notes.server.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseResponse {
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "email")
    private String email;
    @JsonProperty(value = "is_owner")
    private Boolean isOwner;

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

    public Boolean getIsOwner() {
        return isOwner;
    }

    public void setIsOwner(Boolean isOwner) {
        this.isOwner = isOwner;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", isOwner=" + isOwner +
                '}';
    }
}
