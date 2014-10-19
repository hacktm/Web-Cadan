package com.meeting.notes.server.models.sql.services;

import com.meeting.notes.server.models.ClientSessionModel;
import com.meeting.notes.server.models.sql.dao.MeetingDaoImpl;
import com.meeting.notes.server.models.sql.dao.UserDaoImpl;
import com.meeting.notes.server.models.sql.dao.UserMeetingDaoImpl;
import com.meeting.notes.server.models.sql.entities.MeetingEntity;
import com.meeting.notes.server.models.sql.entities.UserEntity;
import com.meeting.notes.server.models.sql.entities.UserMeetingEntity;
import com.meeting.notes.server.models.transport.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by N30 on 10/18/2014.
 */
@Component
public class UserMeetingService {
    private static final Logger LOGGER = Logger.getLogger(UserMeetingService.class);

    @Autowired
    private MeetingDaoImpl meetingDao;
    @Autowired
    private UserMeetingDaoImpl userMeetingDao;
    @Autowired
    private UserDaoImpl userDao;

    public List<ClientSessionModel> getAllClosedMeetings(String email) {
        List<ClientSessionModel> clientSessionModels = new ArrayList<>();
        UserEntity currentUser = userDao.get(email);
        if(null != currentUser) {
            List<UserMeetingEntity> userMeetingEntityList = userMeetingDao.getAllUserMeetings(currentUser.getUserId());
            List<UserMeetingEntity> userMeetingEntityListOwner = userMeetingDao.getUserMeetingHeOwns(currentUser.getUserId());

            List<User> userList = new ArrayList<>();
            for (UserMeetingEntity userMeetingEntity : userMeetingEntityList) {
                UserEntity userEntity = userDao.get(userMeetingEntity.getUserId());
                User user = new User();
                user.setEmail(userEntity.getEmail());
                user.setUsername(userEntity.getUserName());
                user.setName(userEntity.getName());
                userList.add(user);
            }

            for (UserMeetingEntity userMeetingEntity : userMeetingEntityListOwner) {
                ClientSessionModel clientSessionModel = new ClientSessionModel();

                UserEntity thisUserEntity = userDao.get(userMeetingEntity.getUserId());
                clientSessionModel.setUsername(thisUserEntity.getUserName());
                clientSessionModel.setEmail(thisUserEntity.getEmail());
                clientSessionModel.setName(thisUserEntity.getName());
                clientSessionModel.setSessionId(String.valueOf(userMeetingEntity.getMeetingId()));

                MeetingEntity meetingEntity = meetingDao.get(userMeetingEntity.getMeetingId());
                clientSessionModel.setDescription(meetingEntity.getDescription());

                clientSessionModel.setStartTime(meetingEntity.getMeetinStartDate().toString());
                clientSessionModel.setConnectedUsers(userList);
                clientSessionModels.add(clientSessionModel);
            }
        }

        return clientSessionModels;
    }

    public void insertData(ClientSessionModel clientSessionModel) {
        UserEntity userOwnerEntity = new UserEntity();
        userOwnerEntity.setName(clientSessionModel.getName());
        userOwnerEntity.setUserName(clientSessionModel.getUsername());
        userOwnerEntity.setEmail(clientSessionModel.getEmail());
        userDao.insert(userOwnerEntity);

        UserEntity newUserOwnerEntity = userDao.get(clientSessionModel.getEmail());
        MeetingEntity meetingEntity = new MeetingEntity();
        meetingEntity.setDescription(clientSessionModel.getDescription());
        meetingEntity.setSessionId(clientSessionModel.getSessionId());
        meetingEntity.setIsActive(Boolean.FALSE);
        meetingDao.insert(meetingEntity);

        MeetingEntity newMeetingEntity = meetingDao.get(clientSessionModel.getSessionId());
        UserMeetingEntity userMeetingEntity = new UserMeetingEntity();
        userMeetingEntity.setOwner(Boolean.TRUE);
        userMeetingEntity.setMeetingId(newMeetingEntity.getMeetingId());
        userMeetingEntity.setUserId(newUserOwnerEntity.getUserId());
        userMeetingDao.insert(userMeetingEntity);

        List<User> userList = clientSessionModel.getConnectedUsers();
        for(User user : userList) {
            UserEntity userEntity = new UserEntity();
            userEntity.setName(user.getName());
            userEntity.setEmail(user.getEmail());
            userEntity.setUserName(user.getUsername());
            userDao.insert(userEntity);

            UserEntity newUserEntity = userDao.get(user.getEmail());
            userMeetingEntity = new UserMeetingEntity();
            userMeetingEntity.setOwner(Boolean.FALSE);
            userMeetingEntity.setMeetingId(newMeetingEntity.getMeetingId());
            userMeetingEntity.setUserId(newUserEntity.getUserId());
            userMeetingDao.insert(userMeetingEntity);
        }
    }
}
