package com.iot.client.repository;

import com.iot.client.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author datdv
 */
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity , Long> {
    List<RoleEntity> findAllByCode(String code);
    List<RoleEntity> findAllByIdIn(List<Long> roleIds);
}
