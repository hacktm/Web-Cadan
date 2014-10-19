package com.meeting.notes.server.models.ws;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meeting.notes.server.models.MeetingNoteModel;

import java.util.List;

public class CommentForMeetingNote extends BaseWsModel {

    public CommentForMeetingNote() {
        super();
    }

    @JsonProperty(value = "email")
    private String email;
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "data")
    private String data;
    @JsonProperty(value = "row_id")
    private String rowId;
    @JsonProperty(value = "added_date")
    private String addedDate;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    @Override
    public String toString() {
        return "AddNoteForUser{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", data='" + data + '\'' +
                ", addedDate='" + addedDate + '\'' +
                '}';
    }
}
