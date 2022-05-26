package com.iot.client.service.impl;

import com.google.gson.Gson;
import com.google.zxing.WriterException;
import com.iot.client.builder.DeviceBuilder;
import com.iot.client.constants.AppConstant;
import com.iot.client.converter.Converter;
import com.iot.client.dto.DeviceDTO;
import com.iot.client.dto.PriceLevelView;
import com.iot.client.dto.UserDTO;
import com.iot.client.dto.input.DeviceInsertInput;
import com.iot.client.dto.output.DeviceOutput;
import com.iot.client.dto.realtime.Device;
import com.iot.client.dto.realtime.RealtimeWeb;
import com.iot.client.entity.DeviceEntity;
import com.iot.client.entity.PriceLevelEntity;
import com.iot.client.entity.UserEntity;
import com.iot.client.repository.DeviceRepository;
import com.iot.client.repository.PriceLevelRepository;
import com.iot.client.repository.UserRepository;
import com.iot.client.repository.custom.DeviceRepositoryCustom;
import com.iot.client.service.DeviceService;
import com.iot.client.service.UserService;
import com.iot.client.utils.CodeGeneratorUtils;
import com.iot.client.utils.CommonUtils;
import com.iot.client.utils.RealtimeUtils;
import com.iot.client.utils.SecurityUtils;
import com.iot.client.utils.error.CustomException;
import com.iot.client.utils.response.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author datdv
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private PriceLevelRepository priceLevelRepository;
    @Autowired
    private CodeGeneratorUtils codeGeneratorUtils;
    @Autowired
    private RealtimeUtils realtimeUtils;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DeviceRepositoryCustom deviceRepositoryCustom;
    @Autowired
    private UserService userService;


    @Override
    @Transactional
    public DeviceInsertInput save(DeviceInsertInput input) throws IOException, WriterException {
        DeviceEntity entityValidateExist = deviceRepository.findByCode(input.getDevice().getCode());
        if (entityValidateExist != null) {
            throw new CustomException("device code exist", CommonUtils.putError("device", ""));
        }

        DeviceEntity deviceEntity = Converter.toModel(input.getDevice(), DeviceEntity.class);
        deviceEntity.setQrCode(codeGeneratorUtils.generateQRCodeImage(AppConstant.api.payment + String.format("/%s", deviceEntity.getCode()), "/qrcode/device"));
        deviceEntity.setStatus(AppConstant.ACTIVE.INACTIVE_STATUS);

        // add user manager to device
        UserEntity user = userRepository.findOne(input.getDevice().getUserId());
        if (Objects.isNull(user)) {
            throw new CustomException("user not found", CommonUtils.putError("device", ""));
        }
        deviceEntity.setUser(user);

        List<PriceLevelEntity> priceLevelEntities = priceLevelRepository.findByIdIn(input.getPriceLevelIds());
        deviceEntity.setPriceLevels(priceLevelEntities);
        deviceEntity.setUsages(0);

        deviceEntity = deviceRepository.save(deviceEntity);

        input.setDevice(Converter.toModel(deviceEntity, DeviceDTO.class));
        input.setPriceLevelIds(input.getPriceLevelIds());

        return input;
    }

    @Override
    @Transactional
    public DeviceDTO updateStatus(Long id) throws IOException, WriterException {
        DeviceEntity deviceEntity = deviceRepository.findOne(id);
        if (Objects.isNull(deviceEntity)) {
            throw new CustomException("device is null", CommonUtils.putError("deviceId", "ERR_0034"));
        }
        deviceEntity.setStatus(1);
        deviceRepository.save(deviceEntity);
        return Converter.toModel(deviceEntity, DeviceDTO.class);
    }

    @Override
    @Transactional
    public DeviceDTO updateStatus(String code) throws IOException, WriterException {
        DeviceEntity deviceEntity = deviceRepository.findByCode(code);
        if (Objects.isNull(deviceEntity)) {
            throw new CustomException("device is null", CommonUtils.putError("deviceId", "ERR_0034"));
        }
        if (deviceEntity.getStatus().equals(1)) {
            deviceEntity.setStatus(0);
        } else if (deviceEntity.getStatus().equals(0)) {
            deviceEntity.setStatus(1);
        }
        deviceEntity = deviceRepository.save(deviceEntity);
        UserDTO userDTO = userService.findById(SecurityUtils.getPrincipal().getUserId());
        Device device = new Device(deviceEntity.getCode(), 1000, deviceEntity.getStatus(), SecurityUtils.getPrincipal().getUsername());
        RealtimeWeb realtimeWeb = new RealtimeWeb(deviceEntity.getStatus(), deviceEntity.getCode(), userDTO);
        realtimeAfterUpdateStatus(device);
        realtimeAfterUpdateStatus(realtimeWeb);
        return Converter.toModel(deviceEntity, DeviceDTO.class);
    }

    @Override
    public DeviceDTO findById(Long id) {
        DeviceEntity deviceEntity = deviceRepository.findOne(id);
        if (Objects.isNull(deviceEntity)) {
            throw new CustomException("device is null", CommonUtils.putError("deviceId", "ERR_0034"));
        }
        return Converter.toModel(deviceEntity, DeviceDTO.class);
    }

    @Override
    public DeviceDTO updateDevice(Long id, DeviceInsertInput device) throws IOException, WriterException {
        DeviceEntity deviceEntity = deviceRepository.findOne(id);
        if (Objects.isNull(deviceEntity)) {
            throw new CustomException("device is null", CommonUtils.putError("deviceId", "ERR_0034"));
        }
        UserEntity user = userRepository.findOne(device.getDevice().getUserId());
        if (Objects.isNull(user)) {
            throw new CustomException("user not found", CommonUtils.putError("device", ""));
        }
        deviceEntity.setUser(user);
        List<PriceLevelEntity> priceLevel = priceLevelRepository.findByIdIn(device.getPriceLevelIds());
        deviceEntity.setPriceLevels(priceLevel);
        deviceEntity.setName(device.getDevice().getName());
        deviceRepository.save(deviceEntity);
        return Converter.toModel(deviceEntity, DeviceDTO.class);
    }

    @Override
    public DeviceDTO deviceOff(String code) {
        DeviceEntity deviceEntity = deviceRepository.findByCode(code);
        if (Objects.isNull(deviceEntity)) {
            throw new CustomException("device is null", CommonUtils.putError("deviceCode", "ERR_0034"));
        }
        deviceEntity.setStatus(0);
        deviceEntity = deviceRepository.save(deviceEntity);
        RealtimeWeb realtimeWeb = new RealtimeWeb(deviceEntity.getStatus(), deviceEntity.getCode(), null);
        realtimeAfterUpdateStatus(realtimeWeb);
        return Converter.toModel(deviceEntity, DeviceDTO.class);
    }

    @Override
    @Transactional
    public String payment(String deviceCode) {
        DeviceEntity deviceEntity = deviceRepository.findByCode(deviceCode);
        if (deviceEntity.getStatus().equals(AppConstant.ACTIVE.ACTIVE_STATUS)) {
            throw new CustomException("device being used", CommonUtils.putError("device id", ""));
        }
        Device device = Converter.toModel(deviceEntity, Device.class);

        // edit to apply payment online method
        Optional<PriceLevelEntity> priceLevelEntity = deviceEntity.getPriceLevels().stream().filter(item -> item.getPrice() == 10).findAny();
        if (priceLevelEntity.isPresent()) {
            device.setTime(priceLevelEntity.get().getTime());
            device.setStatus(AppConstant.ACTIVE.ACTIVE_STATUS);
        }
        device = (Device) realtimeUtils.postRealtime(AppConstant.api.realtime.DEVICE, device, Device.class);
        if (device != null) {
            int use = deviceEntity.getUsages() + 1;
            deviceEntity.setUsages(use);
            return AppConstant.payment.SUCCESS_MESSAGE;
        }
        return null;
    }

    @Override
    public DeviceOutput findByCodeForQRCheck(String code) {
        DeviceEntity deviceEntity = deviceRepository.findByCode(code);
        DeviceOutput result = new DeviceOutput();
        result.setCode(deviceEntity.getCode());
        result.setFullName(deviceEntity.getUser().getFullName());
        result.setNumberPhone(deviceEntity.getUser().getNumberPhone());
        return result;
    }

    @Override
    @Transactional
    public void delete(List<Long> ids) {
        for (Long id : ids) {
            DeviceEntity deviceEntity = deviceRepository.findOne(id);
            if (Objects.isNull(deviceEntity)) {
                throw new CustomException("device is null", CommonUtils.putError("deviceId", "ERR_0034"));
            }
            deviceEntity.setStatus(0);
            deviceRepository.save(deviceEntity);

        }
    }

    @Override
    public PageList<DeviceOutput> findAll(DeviceBuilder deviceBuilder, Pageable pageable) {
        List<DeviceOutput> deviceOutputList = Converter.toList(deviceRepositoryCustom.findAllDevice(deviceBuilder, pageable), DeviceOutput.class);
        for (DeviceOutput item : deviceOutputList) {
            String timeFromTo = "[" + item.getTimeFormTo() + "]";
            PriceLevelView[] priceLevelViewsArray = new Gson().fromJson(timeFromTo, PriceLevelView[].class);
            item.setLevelViews(Arrays.asList(priceLevelViewsArray));
        }
        Long count = deviceRepositoryCustom.countDevice(deviceBuilder);
        return PageList.<DeviceOutput>builder()
                .list(deviceOutputList)
                .currentPage(pageable.getPageNumber() + 1)
                .total(count)
                .pageSize(pageable.getPageSize())
                .success(true)
                .totalPage((int) Math.ceil((double) Integer.parseInt(count.toString()) / pageable.getPageSize()))
                .build();
    }

    private void realtimeAfterUpdateStatus(Device device) {
        realtimeUtils.postRealtime(AppConstant.api.realtime.DEVICE, device, Device.class);
    }

    private void realtimeAfterUpdateStatus(RealtimeWeb realtimeWeb) {
        realtimeUtils.postRealtime(AppConstant.api.realtime.DEVICE_WEB_PAGE, realtimeWeb, RealtimeWeb.class);
    }


}
