package com.meeting.notes.server.services;

import com.meeting.notes.server.models.ClientSessionModel;
import com.meeting.notes.server.models.requests.BaseRequest;
import com.meeting.notes.server.models.requests.CheckSessionRequest;
import com.meeting.notes.server.models.responses.CheckSessionResponse;
import com.meeting.notes.server.models.transport.Status;
import com.meeting.notes.server.models.transport.User;
import com.meeting.notes.server.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CheckSessionEndpoint extends BaseEndpoint{
    private static final Logger LOGGER = Logger.getLogger(CheckSessionEndpoint.class);

    @RequestMapping(method = RequestMethod.POST, value = "/check.session")
    public @ResponseBody
    CheckSessionResponse checkSession(@RequestBody CheckSessionRequest request) {
        LOGGER.info("Received check session request from email: " + request.getEmail());
        CheckSessionResponse response = new CheckSessionResponse();
        ClientSessionModel sessionModel = getClientsHandler().getSession(request.getSessionId());
        Status status = new Status();
        if (null != sessionModel) {
            boolean isOwner = sessionModel.getEmail().equalsIgnoreCase(request.getEmail());
            response.setIsOwner(isOwner);
            if (isOwner) {
                response.setSessionId(sessionModel.getSessionId());
                status.setStatus(true);
                status.setMessage("");
                response.setUserName(sessionModel.getUsername());
                response.setEmail(sessionModel.getEmail());
                response.setName(sessionModel.getName());
                response.setStatus(status);
                response.setDescription(sessionModel.getDescription());
                response.setUsers(sessionModel.getConnectedUsers());
                response.setOwnerName(sessionModel.getName());
            } else {
                User userSessionModel = sessionModel.getUserByEmail(request.getEmail());
                if (null != userSessionModel) {
                    response.setSessionId(sessionModel.getSessionId());
                    status.setStatus(true);
                    status.setMessage("");
                    response.setUserName(userSessionModel.getUsername());
                    response.setEmail(userSessionModel.getEmail());
                    response.setName(userSessionModel.getName());
                    response.setStatus(status);
                    response.setDescription(sessionModel.getDescription());
                    response.setUsers(sessionModel.getConnectedUsers());
                    response.setOwnerName(sessionModel.getName());
                } else {
                    response.setIsOwner(false);
                    status.setStatus(false);
                    status.setMessage("User has no meeting started");
                    response.setDescription("");
                }
            }
        } else {
            response.setIsOwner(false);
            status.setStatus(false);
            status.setMessage("User has no meeting started");
            response.setDescription("");
        }
        response.setStatus(status);
        return response;
    }

}
