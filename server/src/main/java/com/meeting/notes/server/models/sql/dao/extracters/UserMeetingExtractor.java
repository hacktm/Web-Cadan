package com.meeting.notes.server.models.sql.dao.extracters;

import com.meeting.notes.server.models.sql.entities.UserMeetingEntity;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by N30 on 10/18/2014.
 */
public class UserMeetingExtractor implements ResultSetExtractor {
    @Override
    public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        UserMeetingEntity userMeetingEntity = new UserMeetingEntity();
        userMeetingEntity.setUserMeetingId(resultSet.getInt("UserMeetingsId"));
        userMeetingEntity.setUserId(resultSet.getInt("UserId"));
        userMeetingEntity.setMeetingId(resultSet.getInt("MeetingId"));
        userMeetingEntity.setOwner(resultSet.getBoolean("Owner"));
        return userMeetingEntity;
    }
}
