package com.iot.client.api.admin;

import com.iot.client.utils.response.ResponseEntityBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author datvt
 */

@RestController
@RequestMapping("/api/admin/check")

public class CheckOnlineAPI {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> checkOnline() {
        return ResponseEntityBuilder.getBuilder().
                setMessage("CHECK SUCCESS").
                setDetails(true).build();
    }
}
