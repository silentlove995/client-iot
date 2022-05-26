package com.iot.client.dto.input;

import com.iot.client.dto.DeviceDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author datdv
 */
@Getter
@Setter
public class DeviceInsertInput {
    private DeviceDTO device;
    private List<Long> priceLevelIds;
}
