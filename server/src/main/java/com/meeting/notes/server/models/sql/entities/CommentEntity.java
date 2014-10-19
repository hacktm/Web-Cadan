package com.meeting.notes.server.models.sql.entities;

/**
 * Created by N30 on 10/19/2014.
 */
public class CommentEntity {
    private Integer commentId;
    private Integer userId;
    private Integer meetingId;
    private String message;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "CommentEntity{" +
                "commentId=" + commentId +
                ", userId=" + userId +
                ", meetingId=" + meetingId +
                ", message='" + message + '\'' +
                '}';
    }
}
