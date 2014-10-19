package com.meeting.notes.server.services;

import com.meeting.notes.server.models.ClientSessionModel;
import com.meeting.notes.server.models.requests.BaseRequest;
import com.meeting.notes.server.models.responses.EndedSessionsResponse;
import com.meeting.notes.server.models.sql.services.UserMeetingService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ListEndedSessionsEndpoint extends BaseEndpoint{
    private static final Logger LOGGER = Logger.getLogger(ListEndedSessionsEndpoint.class);
    private static final int MAX_HISTORY_RECORDS = 5;

    @Autowired
    private UserMeetingService userMeetingService;

    @RequestMapping(method = RequestMethod.POST, value = "/get.ended.sessions")
    public @ResponseBody
    EndedSessionsResponse joinSession(@RequestBody BaseRequest request) {
        LOGGER.info("Received ended sessions list request for email: " + request.getEmail());
        if (getClientsHandler().userHasActiveMeeting(request.getEmail())) {
            //return error, client already has started a session
//            return null;
        }
        //get session list from db and set it in the response
        EndedSessionsResponse response = new EndedSessionsResponse();

        List<ClientSessionModel> clientSessionModels = userMeetingService.getAllClosedMeetings(request.getEmail());
        List<ClientSessionModel> returnedClientSessionModels = new ArrayList<>();
        if(clientSessionModels.size() > MAX_HISTORY_RECORDS) {
            for (int i = 0; i < MAX_HISTORY_RECORDS; i++) {
                returnedClientSessionModels.add(clientSessionModels.get(i));
            }
            response.setEndedSessions(returnedClientSessionModels);
        } else {
            response.setEndedSessions(clientSessionModels);
        }

        LOGGER.info("EndedSessionsResponse : " + response);
        return response;
    }

}
