package com.iot.client.service;

import com.google.zxing.WriterException;
import com.iot.client.builder.DeviceBuilder;
import com.iot.client.dto.DeviceDTO;
import com.iot.client.dto.input.DeviceInsertInput;
import com.iot.client.dto.output.DeviceOutput;
import com.iot.client.dto.realtime.Device;
import com.iot.client.utils.response.PageList;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface DeviceService {
    DeviceInsertInput save(DeviceInsertInput input) throws IOException, WriterException;
    String payment(String deviceCode);
    DeviceOutput findByCodeForQRCheck(String code);
    void delete(List<Long> ids);
    PageList<DeviceOutput> findAll(DeviceBuilder deviceBuilder, Pageable pageable);
    DeviceDTO updateStatus(Long id) throws IOException, WriterException;
    DeviceDTO updateStatus(String code) throws IOException, WriterException;
    DeviceDTO findById(Long id);
    DeviceDTO updateDevice(Long id, DeviceInsertInput deviceInsertInput) throws IOException, WriterException;
    DeviceDTO deviceOff(String code);

}
