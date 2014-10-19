package com.meeting.notes.server.models.sql.dao.interfaces;

import com.meeting.notes.server.models.sql.entities.UserEntity;

public interface UserDao {

    boolean insert(UserEntity userEntity);
    boolean delete(UserEntity userEntity);
    boolean delete(Integer usersId);
    boolean update(UserEntity userEntity);
    UserEntity get(Integer usersId);
}
