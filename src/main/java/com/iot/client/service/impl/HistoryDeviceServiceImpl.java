package com.iot.client.service.impl;

import com.iot.client.builder.HistoryDeviceSearchBuilder;
import com.iot.client.dto.output.sqlcustom.HistoryDeviceOutPut;
import com.iot.client.repository.custom.HistoryDeviceRepositoryCustom;
import com.iot.client.service.HistoryDeviceService;
import com.iot.client.utils.response.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author datdv
 */
@Service
public class HistoryDeviceServiceImpl implements HistoryDeviceService {

    @Autowired
    private HistoryDeviceRepositoryCustom historyDeviceRepositoryCustom;

    @Override
    public PageList<HistoryDeviceOutPut> findAllHistoryDeviceByCondition(HistoryDeviceSearchBuilder builder, Pageable pageable) {
        List<HistoryDeviceOutPut> historyDeviceOutPuts = historyDeviceRepositoryCustom.findAllHistoryDeviceByCondition(builder , pageable);
        Long count = historyDeviceRepositoryCustom.countHistoryDeviceByCondition(builder);
        return PageList.<HistoryDeviceOutPut>builder()
                .list(historyDeviceOutPuts)
                .currentPage(pageable.getPageNumber() + 1)
                .total(count)
                .pageSize(pageable.getPageSize())
                .success(true)
                .totalPage((int) Math.ceil((double) Integer.parseInt(count.toString()) / pageable.getPageSize()))
                .build();
    }
}
