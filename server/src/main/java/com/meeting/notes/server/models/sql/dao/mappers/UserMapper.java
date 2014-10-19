package com.meeting.notes.server.models.sql.dao.mappers;

import com.meeting.notes.server.models.sql.dao.extracters.UserExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by N30 on 10/18/2014.
 */
public class UserMapper implements RowMapper{
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        return new UserExtractor().extractData(resultSet);
    }
}
