package com.iot.client.dto.input;

import lombok.Getter;
import lombok.Setter;

/**
 * @author datdv
 */
@Getter
@Setter
public class LoginWithDevice {
    private String phoneNumber;
    private String pin;
}
