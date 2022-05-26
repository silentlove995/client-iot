package com.iot.client.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author datdv
 */
@Component
public class RealtimeUtils {

    private final String pathRealtimeServer = "http://112.213.87.159:9092";

    @Autowired
    private RestTemplate restTemplate;

    public Object postRealtime(String uri , Object request , Class zClassResponse) {
        Object response = restTemplate.postForObject(pathRealtimeServer + uri , request , zClassResponse);
        return response;
    }
}
