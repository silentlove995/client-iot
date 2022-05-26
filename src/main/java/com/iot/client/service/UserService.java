package com.iot.client.service;

import com.iot.client.dto.UserDTO;
import com.iot.client.dto.output.sqlcustom.UserOutPut;
import com.iot.client.dto.realtime.Device;
import com.iot.client.utils.response.PageList;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    UserDTO register(UserDTO user);
    UserDTO save(UserDTO user);
    List<UserDTO> getAllUser();
    PageList<UserOutPut> getAllUserByCondition(Pageable pageable);
    PageList<UserOutPut> getAllUserByCondition(Pageable pageable, String name);
    void realtimeAfterUpdateStatus(Device device);
    UserDTO findById(Long id);
}
