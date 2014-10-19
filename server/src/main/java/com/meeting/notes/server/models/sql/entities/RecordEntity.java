package com.meeting.notes.server.models.sql.entities;

import java.sql.Timestamp;

public class RecordEntity {
    private Integer recordId;
    private Integer userId;
    private Integer meetingId;
    private String recordData;
    private Boolean isPrivate;
    private Timestamp dateTime;

    public String getRecordData() {
        return recordData;
    }

    public void setRecordData(String recordData) {
        this.recordData = recordData;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Integer meetingId) {
        this.meetingId = meetingId;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Record{" +
                "recordId=" + recordId +
                ", userId=" + userId +
                ", meetingId=" + meetingId +
                ", recordData='" + recordData + '\'' +
                ", isPrivate=" + isPrivate +
                ", dateTime=" + dateTime +
                '}';
    }
}
