package com.iot.client.repository;

import com.iot.client.entity.SystemCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author datdv
 */
@Repository
public interface SystemCardRepository extends JpaRepository<SystemCardEntity , Long> {

    @Query(value = "SELECT s1.* FROM system_card s1 WHERE s1.id = (SELECT MAX(s2.id) FROM system_card s2)" , nativeQuery = true)
    SystemCardEntity findLastItem();

    Optional<SystemCardEntity> findByCodeCard(String codeCard);
    List<SystemCardEntity> findAllByPrice(Integer price);
 }
