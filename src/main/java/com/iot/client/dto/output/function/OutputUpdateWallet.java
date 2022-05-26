package com.iot.client.dto.output.function;

import com.iot.client.entity.PriceLevelEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author datdv
 */
@Getter
@Setter
public class OutputUpdateWallet {
    private Integer totalPayment;
    private PriceLevelEntity priceLevel;
}
