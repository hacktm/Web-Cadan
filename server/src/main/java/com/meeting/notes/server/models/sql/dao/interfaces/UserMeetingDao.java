package com.meeting.notes.server.models.sql.dao.interfaces;

import com.meeting.notes.server.models.sql.entities.UserMeetingEntity;

public interface UserMeetingDao {
    boolean insert(UserMeetingEntity meeting);
    boolean delete(UserMeetingEntity meeting);
    boolean delete(Integer MeetingId);
    boolean update(UserMeetingEntity meeting);
    UserMeetingEntity get(Integer meetingId);
}
