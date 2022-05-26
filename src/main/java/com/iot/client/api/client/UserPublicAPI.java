package com.iot.client.api.client;

import com.google.zxing.WriterException;
import com.iot.client.constants.AppConstant;
import com.iot.client.dto.SystemCardDTO;
import com.iot.client.dto.realtime.Device;
import com.iot.client.service.SystemCardService;
import com.iot.client.service.UserService;
import com.iot.client.utils.response.ResponseEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/user")
public class UserPublicAPI {

    @Autowired
    private UserService userService;

    @Autowired
    private SystemCardService systemCardService;

    @RequestMapping(value = "/{code}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateStatus(@PathVariable("code") String code) throws IOException, WriterException {
        Device device = new Device();
        userService.realtimeAfterUpdateStatus(device);
        return ResponseEntityBuilder.getBuilder().setMessage(AppConstant.message.device.UPDATE_SUCCESS).build();
    }

    /**
     * updateStatus
     *
     * @param updateStatus :
     * @return :
     */
    @RequestMapping(value = "/status" , method = RequestMethod.PUT)
    public ResponseEntity<?> updateStatus(@RequestBody SystemCardDTO updateStatus){
        systemCardService.updateStatus(updateStatus);
        return ResponseEntityBuilder.getBuilder().setMessage(AppConstant.message.systemCard.UPDATE_STATUS_SUCCESS).build();
    }
}
