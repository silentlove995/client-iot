package com.iot.client.repository;

import com.iot.client.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author datdv
 */
@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    TransactionEntity findByPhoneNumberAndIsDeleted(String numberPhone , int isDeleted);
}
