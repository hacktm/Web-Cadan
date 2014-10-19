package com.meeting.notes.server.models.sql.dao;

import com.meeting.notes.server.models.sql.dao.interfaces.UserMeetingDao;
import com.meeting.notes.server.models.sql.dao.mappers.UserMeetingMapper;
import com.meeting.notes.server.models.sql.entities.UserMeetingEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by N30 on 10/18/2014.
 */
@Component
public class UserMeetingDaoImpl implements UserMeetingDao{
    private static final Logger LOGGER = Logger.getLogger(UserMeetingDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insert(UserMeetingEntity meeting) {
        String sql = "INSERT INTO user_meetings (UserId, MeetingId, Owner) VALUES (?, ?, ?)";
        int rowsInserted = jdbcTemplate.update(sql, new Object[]{meeting.getUserId(), meeting.getMeetingId(), meeting.getOwner()});
        if(rowsInserted > 0) {
            LOGGER.info("UserMeetingEntity inserted into DataBse: " + meeting);
            return true;
        }
        LOGGER.warn("Failed to insert into DataBase UserMeetingEntity: " + meeting);
        return false;
    }

    @Override
    public boolean delete(UserMeetingEntity meeting) {
        return delete(meeting.getUserId());
    }

    @Override
    public boolean delete(Integer MeetingId) {
        String sql = "DELETE FROM user_meetings WHERE UserMeetingsId=?";
        int rowsDeleted= jdbcTemplate.update(sql,new Object[]{MeetingId});
        if(rowsDeleted>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean update(UserMeetingEntity meeting) {
        return false;
    }

    @Override
    public UserMeetingEntity get(Integer meetingId) {
        List<UserMeetingEntity> list = getAllUserMeetings(meetingId);
        UserMeetingEntity userMeetingEntity = null;
        if(list.size() > 0) {
            userMeetingEntity = list.get(0);
        }
        LOGGER.info("UserMeetingEntity retrieved from DataBase: " + userMeetingEntity);
        return userMeetingEntity;
    }

    public List<UserMeetingEntity> getAllUserMeetings(Integer userId) {
        String sql = "SELECT * FROM user_meetings WHERE UserId = ?";
        List<UserMeetingEntity> list = jdbcTemplate.query(sql, new Object[]{ userId }, new UserMeetingMapper());
        LOGGER.info("UserMeetingEntity(s) retrieved from DataBase: " + list);
        return list;
    }

    public List<UserMeetingEntity> getUserMeetingHeOwns(Integer userId) {
        String sql = "SELECT * FROM user_meetings WHERE UserId = ? and Owner = 1";
        List<UserMeetingEntity> list = jdbcTemplate.query(sql, new Object[]{ userId }, new UserMeetingMapper());
        LOGGER.info("UserMeetingEntity(s) retrieved from DataBase: " + list);
        return list;
    }




}
