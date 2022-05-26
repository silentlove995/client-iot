package com.iot.client.repository;

import com.iot.client.entity.HistoryPaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author datdv
 */
@Repository
public interface HistoryPaymentRepository extends JpaRepository<HistoryPaymentEntity , Long> {
}
