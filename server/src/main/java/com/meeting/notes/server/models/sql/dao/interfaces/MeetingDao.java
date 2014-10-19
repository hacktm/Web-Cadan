package com.meeting.notes.server.models.sql.dao.interfaces;


import com.meeting.notes.server.models.sql.entities.MeetingEntity;

/**
 * Created by N30 on 10/18/2014.
 */
public interface MeetingDao {

    boolean insert(MeetingEntity meetingEntity);
    boolean delete(MeetingEntity meetingEntity);
    boolean delete(Integer MeetingId);
    boolean update(MeetingEntity meetingEntity);
    MeetingEntity get(Integer meetingId);

}
