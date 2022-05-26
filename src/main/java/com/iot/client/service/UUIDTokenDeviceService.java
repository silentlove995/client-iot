package com.iot.client.service;

import com.iot.client.dto.input.LoginWithDevice;
import com.iot.client.dto.output.TokenLoginWithDevice;

/**
 * @author datdv
 */
public interface UUIDTokenDeviceService {
    TokenLoginWithDevice login(String deviceCode , LoginWithDevice loginWithDevice);
    void updateStatusIsDelete(String token);
}
