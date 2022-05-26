package com.iot.client.api.admin;


import com.google.zxing.WriterException;
import com.iot.client.builder.DeviceBuilder;
import com.iot.client.constants.AppConstant;
import com.iot.client.dto.DeviceDTO;
import com.iot.client.dto.input.DeviceInsertInput;
import com.iot.client.dto.output.DeviceOutput;
import com.iot.client.dto.realtime.Device;
import com.iot.client.service.DeviceService;
import com.iot.client.utils.FormUtil;
import com.iot.client.utils.response.PageList;
import com.iot.client.utils.response.ResponseEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author datdv
 */
@RestController
@RequestMapping("/api/admin/device")
public class DeviceAPI {

    @Autowired
    private DeviceService deviceService;


    /**
     * save
     *
     * @param device
     * @return
     * @throws IOException
     * @throws WriterException
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody DeviceInsertInput device) throws IOException, WriterException {
        device = deviceService.save(device);
        return ResponseEntityBuilder.getBuilder().setDetails(device).setMessage(AppConstant.message.device.SAVE_SUCCESS).build();
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestParam("ids") List<Long> ids) throws IOException, WriterException {
        deviceService.delete(ids);
        return ResponseEntityBuilder.getBuilder().setMessage(AppConstant.message.device.DELETE_SUCCESS).build();
    }

    @RequestMapping(value = "/status/{code}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateStatus(@PathVariable("code") String code) throws IOException, WriterException {
        deviceService.updateStatus(code);
        return ResponseEntityBuilder.getBuilder().setMessage(AppConstant.message.device.UPDATE_SUCCESS).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateDevice(@PathVariable("id") Long id, @RequestBody DeviceInsertInput device) throws IOException, WriterException {
        DeviceDTO deviceDTO = deviceService.updateDevice(id, device);
        return ResponseEntityBuilder.getBuilder().setDetails(deviceDTO).setMessage(AppConstant.message.device.UPDATE_SUCCESS).build();
    }

    @RequestMapping(value = "/off/{code}", method = RequestMethod.GET)
    public ResponseEntity<?> deviceOff(@PathVariable("code") String code) throws IOException, WriterException {
        DeviceDTO deviceDTO = deviceService.deviceOff(code);
        return ResponseEntityBuilder.getBuilder().setDetails(deviceDTO).setMessage(AppConstant.message.device.UPDATE_SUCCESS).build();
    }

    @RequestMapping(value = "/{code}", method = RequestMethod.GET)
    public ResponseEntity<?> findByCode(@PathVariable("code") String code){
        DeviceOutput deviceOutput = deviceService.findByCodeForQRCheck(code);
        return ResponseEntityBuilder.getBuilder().setDetails(deviceOutput).setMessage(AppConstant.message.device.UPDATE_SUCCESS).build();
    }
}
