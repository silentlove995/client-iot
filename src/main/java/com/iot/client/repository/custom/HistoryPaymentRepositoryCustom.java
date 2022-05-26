package com.iot.client.repository.custom;


import com.iot.client.builder.HistoryPaymentBuilder;
import com.iot.client.dto.output.sqlcustom.HistoryPaymentOutPut;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author tuanta
 */

public interface HistoryPaymentRepositoryCustom {
    List<HistoryPaymentOutPut> findAllHistoryPayment(HistoryPaymentBuilder historyPaymentBuilder, Pageable pageable);
    Long countHistoryPayment(HistoryPaymentBuilder historyPaymentBuilder);

}
