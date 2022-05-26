package com.iot.client.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author datdv
 */
@Getter
@Setter
public class DeviceDTO extends com.iot.client.dto.AbstractDTO<DeviceDTO> {
    private String name;
    private String code;
    private String qrCode;
    private Integer status;
    private Integer usages;
    private UserDTO user;
    private Long userId;
}
