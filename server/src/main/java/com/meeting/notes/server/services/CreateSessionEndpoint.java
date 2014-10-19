package com.meeting.notes.server.services;

import com.meeting.notes.server.models.ClientSessionModel;
import com.meeting.notes.server.models.responses.CreateUserSessionResponse;
import com.meeting.notes.server.models.requests.CreateUserSessionRequest;
import com.meeting.notes.server.models.transport.Status;
import com.meeting.notes.server.models.transport.User;
import com.meeting.notes.server.utils.DateUtils;
import com.meeting.notes.server.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.UUID;

@Controller
public class CreateSessionEndpoint extends BaseEndpoint{
    private static final Logger LOGGER = Logger.getLogger(CreateSessionEndpoint.class);
//    private static int val = 0;

    @RequestMapping(method = RequestMethod.POST, value = "/new.session")
    public @ResponseBody
    CreateUserSessionResponse createNewSession(@RequestBody CreateUserSessionRequest requestModel) {
        LOGGER.info("Received from REST msg: "+requestModel);
//        model.setStatus("ret: " + (val));
//        model.setSessionId("cristi");
//        messagingTemplate.convertAndSend("/topic/cristi", "ws: " + (val++));
        CreateUserSessionResponse response = new CreateUserSessionResponse();
        if (getClientsHandler().userHasActiveMeeting(requestModel.getEmail())) {
            //return error, client already has started a session
            ClientSessionModel sessionModel = getClientsHandler().getSessionByOwner(requestModel.getEmail());
            response.setSessionId(sessionModel.getSessionId());
            response.setStatus(new Status(false, "Session already started"));
            response.setUsername(sessionModel.getUsername());
            response.setName(sessionModel.getName());
            response.setEmail(sessionModel.getEmail());
            response.setIsOwner(true);
            response.setDescription(sessionModel.getDescription());
            response.setOwnerName(sessionModel.getName());
        } else {
            ClientSessionModel clientSessionModel = new ClientSessionModel();
            clientSessionModel.setName(requestModel.getName());
            clientSessionModel.setEmail(requestModel.getEmail());
            clientSessionModel.setUsername(StringUtils.extractUsernameFromEmail(requestModel.getEmail()));
            String sessionId = createSessionId();
            clientSessionModel.setSessionId(sessionId);
            clientSessionModel.setDescription(requestModel.getDescription());
            clientSessionModel.setStartTime(DateUtils.getFormattedDateFromDate(new Date()));
            getClientsHandler().addSession(sessionId, clientSessionModel);
            response.setSessionId(sessionId);
            response.setStatus(new Status(true));
            response.setUsername(clientSessionModel.getUsername());
            response.setName(requestModel.getName());
            response.setEmail(requestModel.getEmail());
            response.setIsOwner(true);
            response.setDescription(requestModel.getDescription());
            response.setOwnerName(requestModel.getName());

            User user = new User();
            user.setEmail(requestModel.getEmail());
            user.setName(requestModel.getName());
            user.setUsername(StringUtils.extractUsernameFromEmail(requestModel.getEmail()));
        }
        return response;
    }
    private String createSessionId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-","");
    }
}
