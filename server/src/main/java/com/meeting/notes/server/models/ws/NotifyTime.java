package com.meeting.notes.server.models.ws;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotifyTime extends BaseWsModel {
    @JsonProperty(value = "time")
    private String time;
    @JsonProperty(value = "date")
    private String date;

    public NotifyTime() {
    }

    public NotifyTime(String time, String date, int messageType) {
        this.time = time;
        this.date = date;
        super.setWsMessageType(messageType);
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "NotifyTime{" +
                "time='" + time + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
