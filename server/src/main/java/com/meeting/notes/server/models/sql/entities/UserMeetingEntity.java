package com.meeting.notes.server.models.sql.entities;

public class UserMeetingEntity {
    private Integer userMeetingId;
    private Integer userId;
    private Integer meetingId;
    private Boolean owner;

    public Integer getUserMeetingId() {
        return userMeetingId;
    }

    public void setUserMeetingId(Integer userMeetingId) {
        this.userMeetingId = userMeetingId;
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

    public Boolean getOwner() {
        return owner;
    }

    public void setOwner(Boolean owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "UserMeeting{" +
                "userMeetingId=" + userMeetingId +
                ", userId=" + userId +
                ", meetingId=" + meetingId +
                ", owner=" + owner +
                '}';
    }
}
