package com.meeting.notes.server.models.sql.services;

import com.meeting.notes.server.models.sql.dao.RecordDaoImpl;
import com.meeting.notes.server.models.ws.MeetingNoteMessage;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by N30 on 10/19/2014.
 */
public class RecordService {

    @Autowired
    private RecordDaoImpl recordDao;

    public void insert(MeetingNoteMessage meetingNoteMessage, String sessionId) {

    }
}
