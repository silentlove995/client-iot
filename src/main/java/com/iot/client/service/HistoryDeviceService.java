package com.iot.client.service;

import com.iot.client.builder.HistoryDeviceSearchBuilder;
import com.iot.client.dto.output.sqlcustom.HistoryDeviceOutPut;
import com.iot.client.utils.response.PageList;
import org.springframework.data.domain.Pageable;

/**
 * @author datdv
 */
public interface HistoryDeviceService {
    PageList<HistoryDeviceOutPut> findAllHistoryDeviceByCondition(HistoryDeviceSearchBuilder builder , Pageable pageable);
}
