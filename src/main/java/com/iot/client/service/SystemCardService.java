package com.iot.client.service;

import com.iot.client.dto.SystemCardDTO;
import com.iot.client.utils.response.PageList;
import org.springframework.data.domain.Pageable;

/**
 * @author datdv
 */
public interface SystemCardService {
    SystemCardDTO save (SystemCardDTO input);
    void updateStatus(SystemCardDTO updateStatus);
    PageList<SystemCardDTO> getAll(Pageable pageable);
    PageList<SystemCardDTO> findAllByPrice(Pageable pageable, Integer price);
}
