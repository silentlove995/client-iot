package com.iot.client.repository;

import com.iot.client.entity.PriceLevelEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author datdv
 */
@Repository
public interface PriceLevelRepository extends JpaRepository<PriceLevelEntity , Long> {
    List<PriceLevelEntity> findByIdIn(List<Long> ids);
    List<PriceLevelEntity> findByPrice(Integer price);
    PriceLevelEntity findByPriceAndType(Integer price , String type);
    List<PriceLevelEntity> findByType(String type);
    void deleteById(Long id);

    /*@Query(value = "SELECT pri.* FROM price_level pri" , nativeQuery = true)
    List<PriceLevelEntity> findAllPriceLevel(Pageable pageable);*/

    @Query(value = "SELECT COUNT (pri.id) FROM price_level pri" , nativeQuery = true)
    Long countPriceLevelEntities();
}
