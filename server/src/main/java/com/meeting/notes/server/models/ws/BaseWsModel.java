package com.meeting.notes.server.models.ws;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseWsModel {
    @JsonProperty(value = "message_type")
    private Integer wsMessageType;

    public Integer getWsMessageType() {
        return wsMessageType;
    }

    public void setWsMessageType(Integer wsMessageType) {
        this.wsMessageType = wsMessageType;
    }
}
