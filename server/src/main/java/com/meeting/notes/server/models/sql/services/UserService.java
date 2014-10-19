package com.meeting.notes.server.models.sql.services;

import com.meeting.notes.server.models.sql.dao.UserDaoImpl;
import com.meeting.notes.server.models.sql.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by N30 on 10/18/2014.
 */
@Component
public class UserService {

    @Autowired
    private UserDaoImpl userDao;

    public boolean insert(UserEntity userEntity) {
        return userDao.insert(userEntity);
    }

}
