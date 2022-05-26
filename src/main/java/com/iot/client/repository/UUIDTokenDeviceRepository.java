package com.iot.client.repository;

import com.iot.client.entity.UUIDTokenDeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author datdv
 */
@Repository
public interface UUIDTokenDeviceRepository extends JpaRepository<UUIDTokenDeviceEntity , Long> {
    UUIDTokenDeviceEntity findByNumberPhoneUserAndIsDeleted(String numberPhoneUser , int isDeleted);
    UUIDTokenDeviceEntity findByUuidTokenAndIsDeleted(String token , int isDeleted);
}
