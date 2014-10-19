package com.meeting.notes.server.models.sql.entities;

import java.sql.Timestamp;

public class MeetingEntity {
    private Integer meetingId;
    private String sessionId;
    private String description;
    private Timestamp meetinStartDate;
    private Timestamp meetinEndDate;
    private Boolean isActive;

    public Integer getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Integer meetingId) {
        this.meetingId = meetingId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getMeetinStartDate() {
        return meetinStartDate;
    }

    public void setMeetinStartDate(Timestamp meetinStartDate) {
        this.meetinStartDate = meetinStartDate;
    }

    public Timestamp getMeetinEndDate() {
        return meetinEndDate;
    }

    public void setMeetinEndDate(Timestamp meetinEndDate) {
        this.meetinEndDate = meetinEndDate;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "MeetingEntity{" +
                "meetingId=" + meetingId +
                ", sessionId='" + sessionId + '\'' +
                ", description='" + description + '\'' +
                ", meetinStartDate=" + meetinStartDate +
                ", meetinEndDate=" + meetinEndDate +
                ", isActive=" + isActive +
                '}';
    }
}
