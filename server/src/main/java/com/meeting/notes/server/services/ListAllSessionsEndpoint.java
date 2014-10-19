package com.meeting.notes.server.services;

import com.meeting.notes.server.models.ClientSessionModel;
import com.meeting.notes.server.models.requests.BaseRequest;
import com.meeting.notes.server.models.responses.AllSessionsResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ListAllSessionsEndpoint extends BaseEndpoint{
    private static final Logger LOGGER = Logger.getLogger(ListAllSessionsEndpoint.class);

    @RequestMapping(method = RequestMethod.POST, value = "/get.all.active.sessions")
    public @ResponseBody
    AllSessionsResponse getAllActiveSessions(@RequestBody BaseRequest request) {
        LOGGER.info("Received all active sessions list request for email: " + request.getEmail());
        AllSessionsResponse response = new AllSessionsResponse();
        List<ClientSessionModel> list = new ArrayList<>();

        if (getClientsHandler().userHasActiveMeeting(request.getEmail())) {
            list.add(getClientsHandler().getSessionByOwner(request.getEmail()));
        }else {
            for (Map.Entry<String, ClientSessionModel> s : getClientsHandler().getSessionMap().entrySet()) {
                list.add(s.getValue());
            }
        }
        response.setActiveSessions(list);
        return response;
    }

}
