package com.meeting.notes.server.models.sql.dao.extracters;

import com.meeting.notes.server.models.sql.entities.UserEntity;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by N30 on 10/18/2014.
 */
public class UserExtractor implements ResultSetExtractor {
    @Override
    public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(resultSet.getInt("UserId"));
        userEntity.setUserName(resultSet.getString("UserName"));
        userEntity.setName(resultSet.getString("Name"));
        userEntity.setEmail(resultSet.getString("Email"));
        return userEntity;
    }
}
