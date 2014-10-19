package com.meeting.notes.server.models.sql.dao.extracters;

import com.meeting.notes.server.models.sql.entities.MeetingEntity;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MeetingExtractor implements ResultSetExtractor {
    @Override
    public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        MeetingEntity meetingEntity = new MeetingEntity();
        meetingEntity.setMeetingId(resultSet.getInt("MeetingsId"));
        meetingEntity.setDescription(resultSet.getString("Description"));
        meetingEntity.setMeetinStartDate(resultSet.getTimestamp("MeetingStartDate"));
        meetingEntity.setMeetinEndDate(resultSet.getTimestamp("MeetingEndDate"));
        meetingEntity.setIsActive(resultSet.getBoolean("IsActive"));
        meetingEntity.setSessionId(resultSet.getString("SessionId"));
        return meetingEntity;
    }
}
