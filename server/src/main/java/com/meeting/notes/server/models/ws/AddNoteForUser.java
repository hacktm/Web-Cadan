package com.meeting.notes.server.models.ws;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meeting.notes.server.models.MeetingNoteModel;
import com.meeting.notes.server.models.requests.BaseRequest;

import java.sql.Timestamp;
import java.util.List;

public class AddNoteForUser extends BaseWsModel {

    public AddNoteForUser() {
        super();
    }

    @JsonProperty(value = "email")
    private String email;
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "data")
    private String data;
    @JsonProperty(value = "is_private")
    private Boolean isPrivate;
    @JsonProperty(value = "note_id")
    private String noteId;
    @JsonProperty(value = "added_date")
    private String addedDate;
    @JsonProperty(value = "notes")
    private List<MeetingNoteModel> meetingNote;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MeetingNoteModel> getMeetingNote() {
        return meetingNote;
    }

    public void setMeetingNote(List<MeetingNoteModel> meetingNote) {
        this.meetingNote = meetingNote;
    }

    public void addMeetingNote(MeetingNoteModel model) {
        meetingNote.add(model);
    }

    @Override
    public String toString() {
        return "AddNoteForUser{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", data='" + data + '\'' +
                ", isPrivate=" + isPrivate +
                ", noteId='" + noteId + '\'' +
                ", addedDate='" + addedDate + '\'' +
                ", meetingNote=" + meetingNote +
                '}';
    }
}
