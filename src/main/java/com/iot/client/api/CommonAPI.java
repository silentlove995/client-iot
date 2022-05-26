package com.iot.client.api;

import com.iot.client.constants.AppConstant;
import com.iot.client.dto.UserDTO;
import com.iot.client.dto.output.DeviceOutput;
import com.iot.client.service.DeviceService;
import com.iot.client.service.UserService;
import com.iot.client.utils.response.ResponseEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author datdv
 */
@RestController
@RequestMapping("/public/api/")
public class CommonAPI {

    private final UserService userService;
    private final DeviceService deviceService;

    @Autowired
    public CommonAPI(UserService userService
            , DeviceService deviceService) {
        this.userService = userService;
        this.deviceService = deviceService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        userService.register(userDTO);
        return ResponseEntityBuilder.getBuilder().setMessage(AppConstant.message.user.REGISTER_SUCCESS).build();
    }

    @RequestMapping(value = "/payment/{device_code}", method = RequestMethod.GET)
    public ResponseEntity<?> payment(@PathVariable("device_code") String deviceCode) {
        String result = deviceService.payment(deviceCode);
        return ResponseEntityBuilder.getBuilder().setMessage(result).build();
    }

    @RequestMapping(value = "/{device_code}" , method = RequestMethod.GET)
    public ResponseEntity<?> getDeviceByCodeQRCheck(@PathVariable("device_code") String deviceCode) {
        DeviceOutput output = deviceService.findByCodeForQRCheck(deviceCode);
        return ResponseEntityBuilder.getBuilder().setDetails(output).setMessage(AppConstant.message.commonApi.GET_DEVICE_SUCCESS).build();
    }
}
