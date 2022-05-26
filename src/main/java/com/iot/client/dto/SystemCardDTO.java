package com.iot.client.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author datdv
 */
@Getter
@Setter
public class SystemCardDTO extends AbstractDTO<SystemCardDTO> {

    private String codeCard;
    private Integer price;
    private Integer lastCode;
    private Integer status;
    private Long userId;
    private String phoneNumber;
    private Boolean isMobile;
    private String deviceCode;

}
