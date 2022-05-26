package com.iot.client.api.client;

import com.iot.client.constants.AppConstant;
import com.iot.client.dto.input.LoginWithDevice;
import com.iot.client.dto.output.TokenLoginWithDevice;
import com.iot.client.service.UUIDTokenDeviceService;
import com.iot.client.utils.response.ResponseEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author datdv
 */
@RestController
@RequestMapping("/public/api")
public class DevicePublicAPI {

    private final UUIDTokenDeviceService uuidTokenDeviceService;

    @Autowired
    public DevicePublicAPI(UUIDTokenDeviceService uuidTokenDeviceService) {
        this.uuidTokenDeviceService = uuidTokenDeviceService;
    }

    @RequestMapping(value = "/device/login/{device_code}", method = RequestMethod.POST)
    public ResponseEntity<?> loginWithDevice(@PathVariable("device_code") String deviceCode, @RequestBody LoginWithDevice loginWithDevice) {
        TokenLoginWithDevice output = uuidTokenDeviceService.login(deviceCode, loginWithDevice);
        return ResponseEntityBuilder.getBuilder().setDetails(output).setMessage(AppConstant.message.publicApi.LOGIN_WITH_DEVICE_SUCCESS).build();
    }

    @RequestMapping(value = "/device/revoke/token/{token}" , method = RequestMethod.PUT)
    public ResponseEntity<?> revokeToken(@PathVariable("token") String token) {
        uuidTokenDeviceService.updateStatusIsDelete(token);
        return ResponseEntityBuilder.getBuilder().setMessage(AppConstant.message.publicApi.REVOKE_TOKEN_WITH_DEVICE_SUCCESS).build();
    }
}
