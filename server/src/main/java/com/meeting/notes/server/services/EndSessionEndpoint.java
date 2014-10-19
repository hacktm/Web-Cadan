package com.meeting.notes.server.services;

import com.meeting.notes.server.models.ClientSessionModel;
import com.meeting.notes.server.models.requests.EndSessionRequest;
import com.meeting.notes.server.models.responses.CreateUserSessionResponse;
import com.meeting.notes.server.models.sql.services.UserMeetingService;
import com.meeting.notes.server.models.transport.Status;
import com.meeting.notes.server.models.transport.User;
import com.meeting.notes.server.models.ws.OwnerEndSession;
import com.meeting.notes.server.models.ws.UserLeftSession;
import com.meeting.notes.server.models.ws.WsMessageType;
import com.meeting.notes.server.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class EndSessionEndpoint extends BaseEndpoint {
    private static final Logger LOGGER = Logger.getLogger(EndSessionEndpoint.class);

    @Autowired
    private UserMeetingService userMeetingService;

    @RequestMapping(method = RequestMethod.POST, value = "/end.session")
    public
    @ResponseBody
    CreateUserSessionResponse createNewSession(@RequestBody EndSessionRequest requestModel) {
        LOGGER.info("Received from REST msg: " + requestModel);
        CreateUserSessionResponse response = new CreateUserSessionResponse();

            ClientSessionModel clientSessionModel = getClientsHandler().getSessionByOwner(requestModel.getEmail());
            if (null != clientSessionModel) { //owner -> closing the session
                userMeetingService.insertData(getClientsHandler().getSessionMap().get(requestModel.getSessionId()));
                if (getClientsHandler().getSessionMap().remove(requestModel.getSessionId()) != null) {//successful end session by owner
                    response.setUsername(StringUtils.extractUsernameFromEmail(requestModel.getEmail()));
                    response.setStatus(new Status(true, "Session closed successfully."));
                    response.setSessionId(requestModel.getSessionId());
                    response.setName(requestModel.getName());
                    response.setEmail(requestModel.getEmail());
                    response.setIsOwner(false);
                    response.setOwnerName(requestModel.getName());
                    OwnerEndSession ownerEndSession = new OwnerEndSession();
                    ownerEndSession.setEmail(requestModel.getEmail());
                    ownerEndSession.setSessionId(requestModel.getSessionId());
                    ownerEndSession.setWsMessageType(WsMessageType.ownerEndSession.ordinal());
                    getMessagingTemplate().convertAndSend("/topic/" + requestModel.getSessionId(), ownerEndSession);
                }
            } else { // participant -> leaving the session
                clientSessionModel = getClientsHandler().getSessionMap().get(requestModel.getSessionId());
                clientSessionModel.deleteUser(requestModel.getEmail());
                //left the meeting
                response.setSessionId(requestModel.getSessionId());
                response.setStatus(new Status(true, "Participant successfully left the meeting"));
                response.setEmail(requestModel.getEmail());
                response.setUsername(StringUtils.extractUsernameFromEmail(requestModel.getEmail()));
                response.setName(requestModel.getName());
                response.setIsOwner(false);
                response.setOwnerName(requestModel.getName());
                UserLeftSession userLeftSession = new UserLeftSession();
                userLeftSession.setWsMessageType(WsMessageType.userLeaveSession.ordinal());
                userLeftSession.setEmail(requestModel.getEmail());
                userLeftSession.setSessionId(requestModel.getSessionId());
                userLeftSession.setUser(clientSessionModel.getConnectedUsers());
                LOGGER.debug(userLeftSession);
                getMessagingTemplate().convertAndSend("/topic/" + requestModel.getSessionId(), userLeftSession);

            }
        return response;
    }
}
