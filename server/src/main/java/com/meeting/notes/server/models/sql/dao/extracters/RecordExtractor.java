package com.meeting.notes.server.models.sql.dao.extracters;

import com.meeting.notes.server.models.sql.entities.RecordEntity;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by N30 on 10/18/2014.
 */
public class RecordExtractor implements ResultSetExtractor {
    @Override
    public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        RecordEntity recordEntity = new RecordEntity();
        recordEntity.setRecordId(resultSet.getInt("RecordsId"));
        recordEntity.setUserId(resultSet.getInt("UserId"));
        recordEntity.setMeetingId(resultSet.getInt("MeetingId"));
        recordEntity.setRecordData(resultSet.getString("RecordData"));
        recordEntity.setIsPrivate(resultSet.getBoolean("IsPrivate"));
        recordEntity.setDateTime(resultSet.getTimestamp("Timestamp"));
        return recordEntity;
    }
}
