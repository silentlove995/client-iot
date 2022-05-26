package com.iot.client.service.impl;

import com.iot.client.builder.HistoryPaymentBuilder;
import com.iot.client.converter.Converter;
import com.iot.client.dto.output.sqlcustom.HistoryPaymentOutPut;
import com.iot.client.repository.custom.HistoryPaymentRepositoryCustom;
import com.iot.client.service.HistoryPaymentService;
import com.iot.client.utils.response.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryPaymentServiceImpl implements HistoryPaymentService {

    @Autowired
    private HistoryPaymentRepositoryCustom historyPaymentRepositoryCustom;

    @Override
    public PageList<HistoryPaymentOutPut> findAllHistoryPayment(HistoryPaymentBuilder historyPaymentBuilder, Pageable pageable) {
        List<HistoryPaymentOutPut> historyPaymentOutPuts = historyPaymentRepositoryCustom.findAllHistoryPayment(historyPaymentBuilder,pageable);
        Long count = historyPaymentRepositoryCustom.countHistoryPayment(historyPaymentBuilder);
        List<HistoryPaymentOutPut> list = Converter.toList(historyPaymentOutPuts , HistoryPaymentOutPut.class);
        return PageList.<HistoryPaymentOutPut>builder()
                .list(list)
                .currentPage(pageable.getPageNumber() + 1)
                .total(count)
                .pageSize(pageable.getPageSize())
                .success(true)
                .totalPage((int) Math.ceil((double) Integer.parseInt(count.toString()) / pageable.getPageSize()))
                .build();
    }
}
