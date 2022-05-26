package com.iot.client.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author datdv
 */
@Getter
@Setter
public class PriceLevelDTO extends AbstractDTO<PriceLevelDTO> {

    private Long id;
    private Integer price;
    private Integer timeFrom;
    private Integer timeTo;
    private String type;
}
