package com.iot.client.service;

import com.iot.client.builder.HistoryPaymentBuilder;
import com.iot.client.dto.output.sqlcustom.HistoryPaymentOutPut;
import com.iot.client.utils.response.PageList;
import org.springframework.data.domain.Pageable;

public interface HistoryPaymentService {
    PageList<HistoryPaymentOutPut> findAllHistoryPayment(HistoryPaymentBuilder historyPaymentBuilder, Pageable pageable);
}
