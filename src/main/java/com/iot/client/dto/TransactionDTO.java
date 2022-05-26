package com.iot.client.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author datdv
 */
@Getter
@Setter
public class TransactionDTO extends AbstractDTO<TransactionDTO> {
    private String phoneNumber;
    private Long deviceId;
}
