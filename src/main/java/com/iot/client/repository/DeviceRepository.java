package com.iot.client.repository;

import com.iot.client.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author datdv
 */
@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity , Long> {
    DeviceEntity findByCode(String code);
}
