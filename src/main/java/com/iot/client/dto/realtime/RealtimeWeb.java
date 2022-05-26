package com.iot.client.dto.realtime;

import com.iot.client.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author datdv
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RealtimeWeb {
    private int status;
    private String deviceCode;
    private UserDTO user;
}
