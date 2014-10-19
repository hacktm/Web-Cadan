package com.meeting.notes.server.models.sql.dao.mappers;

import com.meeting.notes.server.models.sql.dao.extracters.UserMeetingExtractor;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMeetingMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        return new UserMeetingExtractor().extractData(resultSet);
    }
}
