package com.meeting.notes.server.models.ws;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MeetingNoteMessage {
    @JsonProperty(value = "message_type")
    private Integer messageType;
    @JsonProperty(value = "message")
    private String message;
    @JsonProperty(value = "is_private")
    private Boolean isPrivate;

    public MeetingNoteMessage() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    @Override
    public String toString() {
        return "MeetingNoteMessage{" +
                "messageType=" + messageType +
                ", message='" + message + '\'' +
                ", isPrivate=" + isPrivate +
                '}';
    }
}
