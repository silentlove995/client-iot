package com.iot.client.repository.custom;

import com.iot.client.builder.DeviceBuilder;
import com.iot.client.dto.output.DeviceOutput;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface DeviceRepositoryCustom {
    List<DeviceOutput> findAllDevice(DeviceBuilder deviceBuilder, Pageable pageable);
    Long countDevice(DeviceBuilder deviceBuilder);
    DeviceOutput findById(Long id);
}
