package com.meeting.notes.server.models.sql.dao;

import com.meeting.notes.server.models.sql.dao.interfaces.RecordsDao;
import com.meeting.notes.server.models.sql.dao.mappers.RecordMapper;
import com.meeting.notes.server.models.sql.entities.RecordEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by N30 on 10/18/2014.
 */
@Component
public class RecordDaoImpl implements RecordsDao {
    private static final Logger LOGGER = Logger.getLogger(RecordDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insert(RecordEntity records) {
        String sql = "INSERT INTO records (UserId, MeetingId, RecordData, IsPrivate, Timestamp) VALUES (?, ?, ?, ?, ?)";
        int rowsInserted = jdbcTemplate.update(sql, new Object[]{records.getUserId(), records.getMeetingId(), records.getRecordData(), records.getIsPrivate(), records.getDateTime()});
        if(rowsInserted > 0) {
            LOGGER.info("RecordEntity inserted into DataBse: " + records);
            return true;
        }
        LOGGER.warn("Failed to insert into DataBase RecordEntity: " + records);
        return false;
    }

    @Override
    public boolean delete(RecordEntity records) {
        return delete(records.getRecordId());
    }

    @Override
    public boolean delete(Integer recordsId) {
        String sql = "DELETE FROM records WHERE RecordsId = ?";
        if(jdbcTemplate.update(sql, new Object[] {recordsId }) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(RecordEntity records) {
        return false;
    }

    @Override
    public RecordEntity get(Integer recordId) {
        String sql = "SELECT * FROM records WHERE RecordsId = ?";
        List<RecordEntity> list = jdbcTemplate.query(sql, new Object[]{ recordId }, new RecordMapper());
        RecordEntity record = null;
        if(null != list) {
            record = list.get(0);
        }
        LOGGER.info("RecordEntity retrieved from DataBase: " + record);
        return record;
    }
}
