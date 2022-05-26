package com.iot.client.repository;

import com.iot.client.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity , Long> {
    UserEntity findOneByNumberPhoneAndStatus(String numberPhone, int status);
    UserEntity findById(Long id);
}
