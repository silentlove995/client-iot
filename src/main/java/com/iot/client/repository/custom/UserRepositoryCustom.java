package com.iot.client.repository.custom;

import com.iot.client.dto.output.DeviceOutput;
import com.iot.client.dto.output.sqlcustom.UserOutPut;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author datdv
 */
public interface UserRepositoryCustom {
    List<UserOutPut> findUserByFocusRoleAndCreatedUser(String roleFocus , Long userIdCreated ,String name, Pageable pageable);
    Long countUserByFocusRoleAndCreatedUser(String roleFocus , Long userIdCreated, String name);
}
