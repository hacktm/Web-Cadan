package com.meeting.notes.server.models.transport;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class Record {
    @JsonProperty(value = "meeting_id")
    private Integer meetingId;
    @JsonProperty(value = "user_id")
    private Integer userId;
    @JsonProperty(value = "data")
    private String data;
    @JsonProperty(value = "isPrivate")
    private Boolean isPrivate;
    @JsonProperty(value = "added_date")
    private Timestamp addedDate;


    public Integer getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Integer meetingId) {
        this.meetingId = meetingId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public Timestamp getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Timestamp addedDate) {
        this.addedDate = addedDate;
    }

    @Override
    public String toString() {
        return "Record{" +
                "meetingId=" + meetingId +
                ", userId=" + userId +
                ", data='" + data + '\'' +
                ", isPrivate=" + isPrivate +
                ", addedDate=" + addedDate +
                '}';
    }
}
