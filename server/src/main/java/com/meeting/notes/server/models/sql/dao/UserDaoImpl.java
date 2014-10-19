package com.meeting.notes.server.models.sql.dao;

import com.meeting.notes.server.models.sql.dao.interfaces.UserDao;
import com.meeting.notes.server.models.sql.dao.mappers.UserMapper;
import com.meeting.notes.server.models.sql.entities.UserEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by N30 on 10/18/2014.
 */
@Component
public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insert(UserEntity userEntity) {
        if(!exists(userEntity.getEmail())) {
            String sql = "INSERT INTO users (UserName, Name, Email) VALUES (?, ?, ?)";
            int rowsInserted = jdbcTemplate.update(sql, new Object[]{userEntity.getUserName(), userEntity.getName(), userEntity.getEmail()});
            if (rowsInserted > 0) {
                LOGGER.info("UserEntity inserted into DataBse: " + userEntity);
                return true;
            }
        }
        LOGGER.warn("Failed to insert into DataBase UserEntity: " + userEntity);
        return false;
    }

    @Override
    public boolean delete(UserEntity userEntity) {
        return delete(userEntity.getUserId());
    }

    @Override
    public boolean delete(Integer usersId) {
        String sql = "DELETE FROM users WHERE UserId = ?";
        if(jdbcTemplate.update(sql, new Object[] {usersId }) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(UserEntity userEntity) {
        return false;
    }

    @Override
    public UserEntity get(Integer usersId) {
        String sql = "SELECT * FROM users WHERE UserId = ?";
        List<UserEntity> list = jdbcTemplate.query(sql, new Object[]{ usersId }, new UserMapper());
        UserEntity userEntity = null;
        if(null != list) {
            userEntity = list.get(0);
        }
        LOGGER.info("UserEntity retrieved from DataBase: " + userEntity);
        return userEntity;
    }

    public UserEntity get(String email) {
        String sql = "SELECT * FROM users WHERE Email = ?";
        List<UserEntity> list = jdbcTemplate.query(sql, new Object[]{ email }, new UserMapper());
        UserEntity userEntity = null;
        if(null != list && list.size() > 0) {
            userEntity = list.get(0);
        }
        LOGGER.info("UserEntity retrieved from DataBase: " + userEntity);
        return userEntity;
    }

    private boolean exists(String email) {
        if(null == get(email)) {
            return false;
        }
        return true;
    }
}
