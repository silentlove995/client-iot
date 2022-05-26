package com.iot.client.service.impl;

import com.iot.client.constants.AppConstant;
import com.iot.client.converter.Converter;
import com.iot.client.dto.SystemCardDTO;
import com.iot.client.dto.realtime.Device;
import com.iot.client.entity.*;
import com.iot.client.repository.*;
import com.iot.client.service.SystemCardService;
import com.iot.client.utils.*;
import com.iot.client.utils.dto.GenCardCodeDTO;
import com.iot.client.utils.error.CustomException;
import com.iot.client.utils.response.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author datdv
 */
@Service
public class SystemCardServiceImpl implements SystemCardService {

    @Autowired
    private  SystemCardRepository systemCardRepository;
    @Autowired
    private  CodeGeneratorUtils codeGeneratorUtils;
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  JwtTokenUtils jwtTokenUtils;
    @Autowired
    private  TransactionRepository transactionRepository;
    @Autowired
    private  DeviceRepository deviceRepository;
    @Autowired
    private  BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private  RoleRepository roleRepository;
    @Autowired
    private  RealtimeUtils realtimeUtils;
    @Autowired
    private  PriceLevelRepository priceLevelRepository;


    @Override
    @Transactional
    public SystemCardDTO save(SystemCardDTO input) {
        SystemCardEntity entity = systemCardRepository.findLastItem();
        GenCardCodeDTO genCardCodeDTO = codeGeneratorUtils.generateCodeCard(entity == null ? 0 : entity.getLastCode());
        SystemCardEntity entityInsert = Converter.toModel(input, SystemCardEntity.class);
        entityInsert.setCodeCard(genCardCodeDTO.getCodeCard());
        entityInsert.setLastCode(genCardCodeDTO.getLastCode());
        entityInsert.setStatus(AppConstant.ACTIVE.ACTIVE_STATUS);
        if (input.getUserId() != null) {
            entityInsert.setUser(getUserById(input.getUserId()));
        }
        entityInsert = systemCardRepository.save(entityInsert);
        return Converter.toModel(entityInsert, SystemCardDTO.class);
    }

    @Override
    @Transactional
    public void updateStatus(SystemCardDTO updateStatus) {
        SystemCardEntity entity = systemCardRepository.findByCodeCard(updateStatus.getCodeCard()).orElseThrow(() -> new CustomException("card not found", CommonUtils.putError(" card id", "")));
        if (entity.getStatus().equals(AppConstant.ACTIVE.INACTIVE_STATUS)) {
            throw new CustomException("card code was use", CommonUtils.putError("cord code", ""));
        }
        entity.setStatus(AppConstant.ACTIVE.INACTIVE_STATUS);
        systemCardRepository.save(entity);
        validatePhoneNumberAndInsert(updateStatus.getPhoneNumber(), entity.getPrice());
        if (updateStatus.getIsMobile()) {
            updateWallet(null, entity.getPrice());
        } else {
            DeviceEntity deviceEntity = deviceRepository.findByCode(updateStatus.getDeviceCode());
            if (Objects.isNull(deviceEntity)) {
                throw new CustomException("device not found" , CommonUtils.putError("DeviceEntity" , ""));
            }
            if (deviceEntity.getStatus().equals(AppConstant.ACTIVE.ACTIVE_STATUS)) {
                throw new CustomException("device being used", CommonUtils.putError("device id", ""));
            }
            saveTransaction(deviceEntity , updateStatus.getPhoneNumber());
            deviceEntity.setStatus(AppConstant.ACTIVE.ACTIVE_STATUS);
            deviceRepository.save(deviceEntity);

            realtime(entity.getPrice(), updateStatus.getPhoneNumber(), deviceEntity.getCode());
        }
    }

    @Override
    public PageList<SystemCardDTO> getAll(Pageable pageable) {
        List<SystemCardDTO> systemCardDTOS = Converter.toList(systemCardRepository.findAll(),SystemCardDTO.class);
        long count = systemCardRepository.count();
        return PageList.<SystemCardDTO>builder()
                .list(systemCardDTOS)
                .currentPage(pageable.getPageNumber() + 1)
                .total(count)
                .pageSize(pageable.getPageSize())
                .success(true)
                .totalPage((int) Math.ceil((double) Integer.parseInt(count + "") / pageable.getPageSize()))
                .build();
    }

    @Override
    public PageList<SystemCardDTO> findAllByPrice(Pageable pageable, Integer price) {
        List<SystemCardDTO> systemCardDTOS = Converter.toList(systemCardRepository.findAllByPrice(price),SystemCardDTO.class);
        long count = systemCardRepository.count();
        return PageList.<SystemCardDTO>builder()
                .list(systemCardDTOS)
                .currentPage(pageable.getPageNumber() + 1)
                .total(count)
                .pageSize(pageable.getPageSize())
                .success(true)
                .totalPage((int) Math.ceil((double) Integer.parseInt(count + "") / pageable.getPageSize()))
                .build();
    }

    private void validatePhoneNumberAndInsert(String phoneNumber, int price) {
        UserEntity userEntity = getUserByPhoneNumberAndStatus(phoneNumber);
        if (userEntity == null) {
            String password = codeGeneratorUtils.randomPassword();
            userEntity = new UserEntity();
            userEntity.setNumberPhone(phoneNumber);
            userEntity.setPassword(passwordEncoder.encode(password));
            userEntity.setStatus(AppConstant.ACTIVE.ACTIVE_STATUS);
            userEntity.setUsername(phoneNumber);
            userEntity.setFullName(phoneNumber);
            userEntity.setWallet(price);
            userEntity.setRoles(roleRepository.findAllByCode(AppConstant.role.ROLE_USER));
            userEntity.setPasswordDemo(password);
            userRepository.save(userEntity);
        } else {
            updateWallet(userEntity, price);
        }
    }

    private void realtime(int price, String phoneNumber, String deviceCode) {
        PriceLevelEntity priceLevelEntity = priceLevelRepository.findByPriceAndType(price , AppConstant.typePriceLevel.TRIAL);
        Device device = new Device();
        device.setTime(priceLevelEntity.getTime());
        device.setUsername(phoneNumber);
        device.setStatus(AppConstant.ACTIVE.ACTIVE_STATUS);
        device.setCode(deviceCode);
        realtimeAfterUpdateStatus(device);
    }

    private void saveTransaction(DeviceEntity deviceEntity , String phoneNumber) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setPhoneNumber(phoneNumber);
        transactionEntity.setDevice(deviceEntity);
        transactionEntity.setStartDate(DateTimeUtils.getDateNow());
        transactionRepository.save(transactionEntity);
    }

    private void updateWallet(UserEntity user, int price) {
        if (user == null) {
            user = getUserById(jwtTokenUtils.getUserIdFromToken());
        }
        int oldWallet = user.getWallet();
        oldWallet += price;
        user.setWallet(oldWallet);
        userRepository.save(user);
    }

    private UserEntity getUserById(Long userId) {
        UserEntity userEntity = userRepository.findOne(userId);
        if (Objects.isNull(userEntity)) {
            throw new CustomException("user not found" , CommonUtils.putError("UserEntity" , ""));
        }
        return userEntity;

    }

    private UserEntity getUserByPhoneNumberAndStatus(String phoneNumber) {
        return userRepository.findOneByNumberPhoneAndStatus(phoneNumber, AppConstant.ACTIVE.ACTIVE_STATUS);
    }

    private void realtimeAfterUpdateStatus(Device device) {
        realtimeUtils.postRealtime(AppConstant.api.realtime.DEVICE, device, Device.class);
    }
}
