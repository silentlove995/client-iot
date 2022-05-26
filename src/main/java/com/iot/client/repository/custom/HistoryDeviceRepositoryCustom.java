package com.iot.client.repository.custom;

import com.iot.client.builder.HistoryDeviceSearchBuilder;
import com.iot.client.dto.output.sqlcustom.HistoryDeviceOutPut;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author datdv
 */
public interface HistoryDeviceRepositoryCustom {
    List<HistoryDeviceOutPut> findAllHistoryDeviceByCondition(HistoryDeviceSearchBuilder builder , Pageable pageable);
    Long countHistoryDeviceByCondition(HistoryDeviceSearchBuilder builder);
}
