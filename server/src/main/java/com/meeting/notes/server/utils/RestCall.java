package com.meeting.notes.server.utils;

import com.meeting.notes.server.models.requests.BaseRequest;
import com.meeting.notes.server.models.requests.CreateUserSessionRequest;
import com.meeting.notes.server.models.responses.EndedSessionsResponse;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

/**
 * Created by N30 on 10/18/2014.
 */
public class RestCall {

    public static void main(String args[]) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/get.ended.sessions";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        BaseRequest baseRequest = new BaseRequest();
        baseRequest.setEmail("aa");
        baseRequest.setName("bb");

        restTemplate.postForObject(url, baseRequest, BaseRequest.class);

        HttpEntity entity = new HttpEntity(baseRequest, headers);
        ResponseEntity<EndedSessionsResponse> out = restTemplate.exchange(url, HttpMethod.POST, entity, EndedSessionsResponse.class);
    }
}
