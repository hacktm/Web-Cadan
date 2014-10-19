package com.meeting.notes.server.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meeting.notes.server.models.transport.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClientSessionModel {
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "email")
    private String email;
    @JsonProperty(value = "username")
    private String username;
    @JsonProperty(value = "session_id")
    private String sessionId;
    @JsonProperty(value = "description")
    private String description;
    @JsonProperty(value = "connected_users")
    private List<User> connectedUsers;
    @JsonProperty(value = "notes")
    private List<MeetingNoteModel> meetingNote;
    @JsonProperty(value = "start_time")
    private String startTime;

    public ClientSessionModel() {
        connectedUsers = new ArrayList<>();
        meetingNote = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public List<User> getConnectedUsers() {
        return connectedUsers;
    }

    public List<MeetingNoteModel> getMeetingNote() {
        return meetingNote;
    }

    public void setMeetingNote(List<MeetingNoteModel> meetingNote) {
        this.meetingNote = meetingNote;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public boolean userAlreadyJoined(String email) {
        for (User user : connectedUsers) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    public User getUserByEmail(String email) {
        for (User user : connectedUsers) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }

    public void deleteUser(String email) {
        Iterator it = connectedUsers.iterator();
        while(it.hasNext()) {
            User user = (User)it.next();
            if (user.getEmail().equalsIgnoreCase(email)) {
                it.remove();
                return;
            }
        }
    }

/*    public void addMeetingNote(MeetingNoteModel model) {
        this.meetingNote = model;
    }*/

    public void addMeetingNote(MeetingNoteModel model, int index) {
        meetingNote.add(index, model);
    }

    public void deleteMeetingNote(MeetingNoteModel model) {
        Iterator it = meetingNote.iterator();
        while(it.hasNext()) {
            MeetingNoteModel meetingNoteModel = (MeetingNoteModel)it.next();
            if (model.getNoteId().equalsIgnoreCase(meetingNoteModel.getNoteId())) {
                it.remove();
                return;
            }
        }
    }

    public void setConnectedUsers(List<User> connectedUsers) {
        this.connectedUsers = connectedUsers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "ClientSessionModel{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", description='" + description + '\'' +
                ", connectedUsers=" + connectedUsers +
                ", meetingNote=" + meetingNote +
                ", startTime='" + startTime + '\'' +
                '}';
    }
}
