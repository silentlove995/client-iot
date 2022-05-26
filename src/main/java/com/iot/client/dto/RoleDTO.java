package com.iot.client.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author datdv
 *
 */
@Getter
@Setter
public class RoleDTO extends com.iot.client.dto.AbstractDTO<RoleDTO> {
    private String name;
    private String code;
}
