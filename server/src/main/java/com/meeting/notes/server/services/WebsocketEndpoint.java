package com.meeting.notes.server.services;

import com.meeting.notes.server.models.ClientSessionModel;
import com.meeting.notes.server.models.ws.*;
import com.meeting.notes.server.utils.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@EnableScheduling
public class WebsocketEndpoint extends BaseEndpoint{
    private static final Logger LOGGER = Logger.getLogger(WebsocketEndpoint.class);

    @MessageMapping("/server-websocket")
    public MeetingNoteMessage receive(MeetingNoteMessage message) {
        LOGGER.info("Received message: " + message);
        getMessagingTemplate().convertAndSend("/topic/cristi", getDate());
        return message;
    }

    @MessageMapping("/server-websocket/{sessionId}")
    public MeetingNoteMessage receiveFromSession(MeetingNoteMessage message, @DestinationVariable String sessionId) {
        LOGGER.info("Received message: " + message+"; sessionId: "+sessionId);
//        messagingTemplate.convertAndSend("/topic/cristi", getDate());
        return message;
    }

    @MessageMapping("/server-websocket/{sessionId}/{userId}")
    public MeetingNoteMessage receiveFromUser(MeetingNoteMessage message, @DestinationVariable String sessionId, @DestinationVariable String userId) {
        LOGGER.info("Received message: " + message+"; sessionId: "+sessionId+"; userId: "+userId);
        AddNoteForUser addNoteForUser = new AddNoteForUser();
        ClientSessionModel sessionModel = getClientsHandler().getSession(sessionId);
        addNoteForUser.setName(sessionModel.getName());
        addNoteForUser.setEmail(sessionModel.getEmail());
        addNoteForUser.setIsPrivate(message.getIsPrivate());
        addNoteForUser.setNoteId("0");
        addNoteForUser.setWsMessageType(WsMessageType.updateMeetingNotes.ordinal());
        addNoteForUser.setAddedDate(DateUtils.getFormattedDateFromDate(new Date()));
        String messageString = message.getMessage();

        addNoteForUser.setData(messageString);
        message.setMessageType(5);

        addNote(addNoteForUser, sessionId, getMessageList(messageString));
        addNoteForUser.setMeetingNote(getClientsHandler().getSession(sessionId).getMeetingNote());
        getMessagingTemplate().convertAndSend("/topic/" + sessionId, addNoteForUser);
        return message;
    }

    @MessageMapping("/server-websocket/{sessionId}/{userId}/{command}")
    public CommentForMeetingNote receiveFromUserCommand(CommentForMeetingNote comment, @DestinationVariable String sessionId, @DestinationVariable String userId, @DestinationVariable String command) {
        LOGGER.info("Received message: " + comment+"; sessionId: "+sessionId+"; userId: "+userId +"; command: " + command);

        comment.setAddedDate(DateUtils.getFormattedDateFromDate(new Date()));
        addCommentForNote(comment, sessionId);
        comment.setWsMessageType(WsMessageType.new_comment.ordinal());

        getMessagingTemplate().convertAndSend("/topic/" + sessionId, comment);
        return comment;
    }


    private String[] getMessageList(String msg) {
        //remove last enter
        if (msg.lastIndexOf("<br>") != -1) {
            msg = msg.substring(0, msg.lastIndexOf("<br>"));
        }
        msg = msg.replace("&nbsp;", " ");
        String[] values = msg.split("<br>");
        return values;
    }

    private String getDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("H:m:s");
        return simpleDateFormat.format(new Date());
    }

    @Scheduled(fixedRate = 1000)
    private void meetingTimer() {
        for (Map.Entry<String, ClientSessionModel> sess : getClientsHandler().getSessionMap().entrySet()) {
            long time = DateUtils.getCurrentDateTime().getTime()-DateUtils.getDateFromString(sess.getValue().getStartTime()).getTime();
            String formatedTime = DateUtils.getFormattedTime(time);
            String formatedDate = DateUtils.getDateFormatted(sess.getValue().getStartTime());
            getMessagingTemplate().convertAndSend("/topic/" + sess.getValue().getSessionId(),
                    new NotifyTime(formatedTime, formatedDate, WsMessageType.timer.ordinal()));
        }
    }
}
