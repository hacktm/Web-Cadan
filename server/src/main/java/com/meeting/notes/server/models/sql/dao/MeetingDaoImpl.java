package com.meeting.notes.server.models.sql.dao;

import com.meeting.notes.server.models.sql.dao.interfaces.MeetingDao;
import com.meeting.notes.server.models.sql.dao.mappers.MeetingMapper;
import com.meeting.notes.server.models.sql.entities.MeetingEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Created by N30 on 10/18/2014.
 */
@Component
public class MeetingDaoImpl implements MeetingDao {
    private static final Logger LOGGER = Logger.getLogger(MeetingDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insert(MeetingEntity meetingEntity) {
        String sql = "INSERT INTO meetings (Description, SessionId, IsActive) VALUES (?, ?, ?)";
        int rowsInserted = jdbcTemplate.update(sql, new Object[]{meetingEntity.getDescription(), meetingEntity.getSessionId(), meetingEntity.getIsActive()});
        if(rowsInserted > 0) {
            LOGGER.info("MeetingEntity inserted into DataBse: " + meetingEntity);
            return true;
        }
        LOGGER.warn("Failed to insert into DataBase MeetingEntity: " + meetingEntity);
        return false;
    }

    @Override
    public boolean delete(MeetingEntity meetingEntity) {
        return delete(meetingEntity.getMeetingId());
    }

    @Override
    public boolean delete(Integer meetingId) {
        String sql = "DELETE FROM meetings WHERE MeetingId = ?";
        if(jdbcTemplate.update(sql, new Object[] {meetingId }) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(MeetingEntity meetingEntity) {
        return false;
    }

    @Override
    public MeetingEntity get(Integer meetingId) {
        String sql = "SELECT * FROM meetings WHERE MeetingsId = ?";
        List<MeetingEntity> list = jdbcTemplate.query(sql, new Object[]{ meetingId }, new MeetingMapper());
        MeetingEntity meetingEntity = null;
        if(null != list) {
            meetingEntity = list.get(0);
        }
        LOGGER.info("MeetingEntity retrieved from DataBase: " + meetingEntity);
        return meetingEntity;
    }

    public MeetingEntity get(String sessionId) {
        String sql = "SELECT * FROM meetings WHERE SessionId = ?";
        List<MeetingEntity> list = jdbcTemplate.query(sql, new Object[]{ sessionId }, new MeetingMapper());
        MeetingEntity meetingEntity = null;
        if(null != list) {
            meetingEntity = list.get(0);
        }
        LOGGER.info("MeetingEntity retrieved from DataBase: " + meetingEntity);
        return meetingEntity;
    }
}
