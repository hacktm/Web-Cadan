package com.meeting.notes.server.services;

import com.meeting.notes.server.models.ClientSessionModel;
import com.meeting.notes.server.models.requests.JoinSessionRequest;
import com.meeting.notes.server.models.responses.JoinSessionResponse;
import com.meeting.notes.server.models.transport.Status;
import com.meeting.notes.server.models.transport.User;
import com.meeting.notes.server.models.ws.NewUserJoinedSession;
import com.meeting.notes.server.models.ws.WsMessageType;
import com.meeting.notes.server.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JoinSessionEndpoint extends BaseEndpoint{
    private static final Logger LOGGER = Logger.getLogger(JoinSessionEndpoint.class);

    @RequestMapping(method = RequestMethod.POST, value = "/join.session")
    public @ResponseBody
    JoinSessionResponse joinSession(@RequestBody JoinSessionRequest request) {
        LOGGER.info("Received join session request from email: " + request.getEmail() + "and sessionId" + request.getSessionId());
        JoinSessionResponse response = new JoinSessionResponse();
        if (getClientsHandler().userHasActiveMeeting(request.getEmail())) {
            //return error, client already has started a session
            ClientSessionModel sessionModel = getClientsHandler().getSessionByOwner(request.getEmail());
            response.setSessionId(sessionModel.getSessionId());
            response.setStatus(new Status(false, "Session already started"));
            response.setName(sessionModel.getName());
            response.setEmail(sessionModel.getEmail());
            response.setUserName(StringUtils.extractUsernameFromEmail(request.getEmail()));
            response.setIsOwner(true);
            response.setDescription(sessionModel.getDescription());
            response.setOwnerName(sessionModel.getName());
//            response.setNotes(sessionModel.getMeetingNote().getData());
            return response;
        }
        response.setName(request.getName());
        response.setEmail(request.getEmail());
        ClientSessionModel clientSessionModel = getClientsHandler().getSession(request.getSessionId());
        if (null != clientSessionModel && !clientSessionModel.userAlreadyJoined(request.getEmail())) {
            User user = new User();
            user.setUsername(StringUtils.extractUsernameFromEmail(request.getEmail()));
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            getClientsHandler().getSession(request.getSessionId()).getConnectedUsers().add(user);
            response.setSessionId(request.getSessionId());
            response.setUsers(getClientsHandler().getSession(request.getSessionId()).getConnectedUsers());
            Status status = new Status();
            status.setMessage("User " + StringUtils.extractUsernameFromEmail(request.getEmail()) + " has joined the meeting.");
            status.setStatus(true);
            response.setStatus(status);
            response.setUserName(StringUtils.extractUsernameFromEmail(request.getEmail()));
            response.setIsOwner(false);
            response.setDescription(getClientsHandler().getSession(request.getSessionId()).getDescription());
            response.setOwnerName(getClientsHandler().getSession(request.getSessionId()).getName());

            //send WS notification
            NewUserJoinedSession userJoinedSession = new NewUserJoinedSession();
            userJoinedSession.setWsMessageType(WsMessageType.newUserJoinedSession.ordinal());
            userJoinedSession.setSessionId(request.getSessionId());
            userJoinedSession.setUser(getClientsHandler().getSession(request.getSessionId()).getConnectedUsers());
            LOGGER.debug(userJoinedSession);
            getMessagingTemplate().convertAndSend("/topic/" + request.getSessionId(), userJoinedSession);

        } else {
            Status status = new Status();
            status.setMessage("An error has occurred while joining the meeting. Username: " + StringUtils.extractUsernameFromEmail(request.getEmail()) + " ");
            status.setStatus(false);
            response.setStatus(status);
            response.setSessionId(request.getSessionId());
            response.setUserName(StringUtils.extractUsernameFromEmail(request.getEmail()));
            response.setIsOwner(false);
            response.setDescription("");
        }
        return response;
    }

}
