package com.meeting.notes.server.services;

import com.meeting.notes.server.handlers.ClientsHandler;
import com.meeting.notes.server.models.ClientSessionModel;
import com.meeting.notes.server.models.MeetingNoteModel;
import com.meeting.notes.server.models.ws.AddNoteForUser;
import com.meeting.notes.server.models.ws.CommentForMeetingNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Arrays;

public abstract class BaseEndpoint {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private ClientsHandler clientsHandler;

    public SimpMessagingTemplate getMessagingTemplate() {
        return messagingTemplate;
    }

    public void setMessagingTemplate(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public ClientsHandler getClientsHandler() {
        return clientsHandler;
    }

    public void setClientsHandler(ClientsHandler clientsHandler) {
        this.clientsHandler = clientsHandler;
    }

    protected void addNote(AddNoteForUser addNoteForUser, String sessionId, String[] notes) {
        ClientSessionModel clientSessionModel = clientsHandler.getSession(sessionId);
        if (null != clientSessionModel) {
            clientSessionModel.getMeetingNote().clear();
            for (int i=0; i<notes.length; i++) {
                MeetingNoteModel model = new MeetingNoteModel();
                model.setAddedDate(addNoteForUser.getAddedDate());
                model.setNoteId(String.valueOf(i));
                model.setData(notes[i]);
                model.setIsPrivate(addNoteForUser.getIsPrivate());
                model.setEmail(addNoteForUser.getEmail());
                model.setName(addNoteForUser.getName());
                clientSessionModel.addMeetingNote(model, i);
            }
        }
    }

    protected void addCommentForNote(CommentForMeetingNote commentForMeetingNote, String sessionId) {
        ClientSessionModel clientSessionModel = clientsHandler.getSession(sessionId);
        if(null != clientSessionModel) {
            MeetingNoteModel comment = new MeetingNoteModel();
            comment.setEmail(commentForMeetingNote.getEmail());
            comment.setName(commentForMeetingNote.getName());
            comment.setAddedDate(commentForMeetingNote.getAddedDate());
            comment.setData(commentForMeetingNote.getData());
            comment.setIsPrivate(false);
            comment.setNoteId(commentForMeetingNote.getRowId());
            clientSessionModel.getMeetingNote().get(Integer.parseInt(commentForMeetingNote.getRowId())).getComments().add(comment);
        }
    }
}