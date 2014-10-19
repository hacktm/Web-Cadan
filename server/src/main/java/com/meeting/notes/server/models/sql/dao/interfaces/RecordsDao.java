package com.meeting.notes.server.models.sql.dao.interfaces;

import com.meeting.notes.server.models.sql.entities.RecordEntity;

/**
 * Created by N30 on 10/18/2014.
 */
public interface RecordsDao {

    boolean insert(RecordEntity records);
    boolean delete(RecordEntity records);
    boolean delete(Integer recordsId);
    boolean update(RecordEntity records);
    RecordEntity get(Integer recordsID);
}
