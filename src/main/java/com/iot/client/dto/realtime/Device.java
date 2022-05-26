package com.iot.client.dto.realtime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Device {
    private String code;
    private Integer time;
    private Integer status;
    private String username;
}
