package com.iot.client.service.impl;

import com.iot.client.constants.AppConstant;
import com.iot.client.converter.Converter;
import com.iot.client.dto.UserDTO;
import com.iot.client.dto.input.LoginWithDevice;
import com.iot.client.dto.output.TokenLoginWithDevice;
import com.iot.client.dto.output.function.OutputUpdateWallet;
import com.iot.client.dto.realtime.RealtimeWeb;
import com.iot.client.dto.shortdto.SortByTimePriceLevel;
import com.iot.client.entity.*;
import com.iot.client.repository.*;
import com.iot.client.service.UUIDTokenDeviceService;
import com.iot.client.utils.CodeGeneratorUtils;
import com.iot.client.utils.CommonUtils;
import com.iot.client.utils.DateTimeUtils;
import com.iot.client.utils.RealtimeUtils;
import com.iot.client.utils.error.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author datdv
 */
@Service
public class UUIDTokenDeviceServiceImpl implements UUIDTokenDeviceService {

    @Autowired
    private UUIDTokenDeviceRepository uuidTokenDeviceRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CodeGeneratorUtils codeGeneratorUtils;
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private HistoryDeviceRepository historyDeviceRepository;
    @Autowired
    private PriceLevelRepository priceLevelRepository;
    @Autowired
    private RealtimeUtils realtimeUtils;


    @Override
    @Transactional
    public TokenLoginWithDevice login(String deviceCode, LoginWithDevice loginWithDevice) {
        validateRequired(loginWithDevice);
        UserEntity user = userRepository.findOneByNumberPhoneAndStatus(loginWithDevice.getPhoneNumber(), AppConstant.ACTIVE.ACTIVE_STATUS);
        if (user == null) {
            throw new CustomException("user not found", CommonUtils.putError("phone number", ""));
        } else if (!passwordEncoder.matches(loginWithDevice.getPin(), user.getPassword())) {
            throw new CustomException("password not match", CommonUtils.putError("phone number", ""));
        }
        DeviceEntity deviceEntity = deviceRepository.findByCode(deviceCode);
        if (deviceEntity == null) {
            throw new CustomException("device not found", CommonUtils.putError("device code", ""));
        } else if (deviceEntity.getStatus().equals(AppConstant.ACTIVE.ACTIVE_STATUS)) {
            throw new CustomException("device is used", CommonUtils.putError("device code", ""));
        }
        UUIDTokenDeviceEntity uuidTokenDeviceEntity = uuidTokenDeviceRepository.findByNumberPhoneUserAndIsDeleted(loginWithDevice.getPhoneNumber(), AppConstant.ACTIVE.ACTIVE_STATUS);
        if (uuidTokenDeviceEntity != null) {
            throw new CustomException("account is used", CommonUtils.putError("phone number user", ""));
        }
        uuidTokenDeviceEntity = new UUIDTokenDeviceEntity();
        uuidTokenDeviceEntity.setDevice(deviceEntity);
        uuidTokenDeviceEntity.setUuidToken(user.getNumberPhone() + "_" + codeGeneratorUtils.randomUUID());
        uuidTokenDeviceEntity.setNumberPhoneUser(user.getNumberPhone());
        uuidTokenDeviceEntity.setIsDeleted(AppConstant.ACTIVE.ACTIVE_STATUS);
        uuidTokenDeviceEntity = uuidTokenDeviceRepository.save(uuidTokenDeviceEntity);
        TokenLoginWithDevice tokenLoginWithDevice = new TokenLoginWithDevice();
        tokenLoginWithDevice.setToken(uuidTokenDeviceEntity.getUuidToken());
        deviceEntity.setStatus(AppConstant.ACTIVE.ACTIVE_STATUS);
        int use = deviceEntity.getUsages() + 1;
        deviceEntity.setUsages(use);
        deviceRepository.save(deviceEntity);
        saveTransaction(deviceEntity, user.getNumberPhone());
        realtimeForWeb(deviceEntity , user);
        return tokenLoginWithDevice;
    }

    private void saveTransaction(DeviceEntity deviceEntity, String phoneNumber) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setDevice(deviceEntity);
        transactionEntity.setPhoneNumber(phoneNumber);
        transactionEntity.setStartDate(DateTimeUtils.getDateNow());
        transactionEntity.setIsDeleted(AppConstant.ACTIVE.ACTIVE_STATUS);
        transactionRepository.save(transactionEntity);
    }

    private void validateRequired(LoginWithDevice loginWithDevice) {
        if (loginWithDevice.getPhoneNumber() == null || loginWithDevice.getPhoneNumber().equals("")) {
            throw new CustomException("phone number not empty", CommonUtils.putError("phone number", ""));
        }
        if (loginWithDevice.getPin() == null || loginWithDevice.getPin().equals("")) {
            throw new CustomException("PIN not empty", CommonUtils.putError("PIN", ""));
        }
    }

    private RealtimeWeb realtimeForWeb(DeviceEntity deviceEntity , UserEntity user) {
        RealtimeWeb realtimeWeb = new RealtimeWeb();
        realtimeWeb.setStatus(AppConstant.ACTIVE.ACTIVE_STATUS);
        realtimeWeb.setDeviceCode(deviceEntity.getCode());
        realtimeWeb.setUser(Converter.toModel(user , UserDTO.class));
        return (RealtimeWeb) realtimeUtils.postRealtime(AppConstant.api.realtime.DEVICE_WEB_PAGE , realtimeWeb , RealtimeWeb.class);
    }

    @Override
    @Transactional
    public void updateStatusIsDelete(String token) {
        UUIDTokenDeviceEntity entity = uuidTokenDeviceRepository.findByUuidTokenAndIsDeleted(token, AppConstant.ACTIVE.ACTIVE_STATUS);
        if (entity == null) {
            throw new CustomException("token not found", CommonUtils.putError("token", ""));
        }
        entity.setIsDeleted(AppConstant.ACTIVE.INACTIVE_STATUS);
        DeviceEntity deviceEntity = entity.getDevice();
        deviceEntity.setStatus(AppConstant.ACTIVE.INACTIVE_STATUS);
        deviceEntity = deviceRepository.save(deviceEntity);
        saveHistoryDevice(deviceEntity, entity.getNumberPhoneUser());
        uuidTokenDeviceRepository.save(entity);
    }

    private void saveHistoryDevice(DeviceEntity deviceEntity, String numberPhone) {
        TransactionEntity transactionEntity = transactionRepository.findByPhoneNumberAndIsDeleted(numberPhone, AppConstant.ACTIVE.ACTIVE_STATUS);
        UserEntity userEntity = userRepository.findOneByNumberPhoneAndStatus(numberPhone, AppConstant.ACTIVE.ACTIVE_STATUS);
        HistoryDeviceEntity historyDeviceEntity = new HistoryDeviceEntity();
        Date endDate = DateTimeUtils.getDateNow();
        historyDeviceEntity.setDevice(deviceEntity);
        historyDeviceEntity.setStartDate(transactionEntity.getStartDate());
        historyDeviceEntity.setEndDate(endDate);
        historyDeviceEntity.setTotalTime(DateTimeUtils.convertToHoursMinuteSecond(transactionEntity.getStartDate(), endDate));
        historyDeviceEntity.setStatus(AppConstant.ACTIVE.ACTIVE_STATUS);
        historyDeviceEntity.setUser(userEntity);
        historyDeviceEntity.setDeleted(AppConstant.ACTIVE.INACTIVE_STATUS);
        OutputUpdateWallet output = updateWalletForUser(userEntity, transactionEntity.getStartDate(), endDate);
        historyDeviceEntity.setTotalPayment(output.getTotalPayment());
        historyDeviceEntity.setPriceLevel(output.getPriceLevel());
        historyDeviceRepository.save(historyDeviceEntity);
        transactionEntity.setIsDeleted(AppConstant.ACTIVE.INACTIVE_STATUS);
        transactionRepository.save(transactionEntity);
    }

    private OutputUpdateWallet updateWalletForUser(UserEntity userEntity, Date startDate, Date endDate) {
        OutputUpdateWallet result = new OutputUpdateWallet();
        int second = DateTimeUtils.getSecond(startDate, endDate);
        PriceLevelEntity priceLevelEntity = getPriceLevel(second);
        int currentWallet = userEntity.getWallet();
        int total = second * priceLevelEntity.getPrice().intValue();
        if (total > currentWallet) {
            throw new CustomException("not enough money", CommonUtils.putError("user wallet", ""));
        }
        userEntity.setWallet(currentWallet - total);
        userRepository.save(userEntity);
        result.setTotalPayment(total);
        result.setPriceLevel(priceLevelEntity);
        return result;
    }

    private PriceLevelEntity getPriceLevel(int second) {
        List<PriceLevelEntity> priceLevels = priceLevelRepository.findAll();
        PriceLevelEntity result;
        PriceLevelEntity[] arr = new PriceLevelEntity[priceLevels.size()];
        PriceLevelEntity[] output = priceLevels.toArray(arr);
        Arrays.sort(output, new SortByTimePriceLevel());
        List<PriceLevelEntity> priceLevelAfterSorts = Arrays.asList(output);

        Optional<PriceLevelEntity> priceLevelEntity = priceLevelAfterSorts.stream().filter(item -> (item.getTimeFrom() <= second && item.getTimeTo() >= second)).findAny();
        result = priceLevelEntity.orElseGet(() -> priceLevelAfterSorts.get(priceLevelAfterSorts.size() - 1));

        return result;
    }
}
