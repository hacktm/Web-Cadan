package com.meeting.notes.server.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class MeetingNoteModel {
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
    @JsonProperty(value = "comments")
    private List<MeetingNoteModel> comments;

    public MeetingNoteModel() {
        comments = new ArrayList<>();
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

    public List<MeetingNoteModel> getComments() {
        return comments;
    }

    public void setComments(List<MeetingNoteModel> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "MeetingNoteModel{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", data='" + data + '\'' +
                ", isPrivate=" + isPrivate +
                ", noteId='" + noteId + '\'' +
                ", addedDate='" + addedDate + '\'' +
                ", comments=" + comments +
                '}';
    }
}
