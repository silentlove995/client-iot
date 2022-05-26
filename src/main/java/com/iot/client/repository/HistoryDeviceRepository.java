package com.iot.client.repository;

import com.iot.client.entity.HistoryDeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author datdv
 */
@Repository
public interface HistoryDeviceRepository extends JpaRepository<HistoryDeviceEntity , Long> {
}
