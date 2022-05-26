package com.iot.client.service;

import com.iot.client.dto.PriceLevelDTO;
import com.iot.client.utils.response.PageList;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PriceLevelService {
    PriceLevelDTO save(PriceLevelDTO input);
    PriceLevelDTO update(Long id , PriceLevelDTO update);
    PriceLevelDTO findOne(Long id);
    void delete(List<Long> id);
    PageList<PriceLevelDTO> findAllPriceLevel(Pageable pageable);
    List<PriceLevelDTO> findAll();
    List<PriceLevelDTO> findByType(String type);
    PageList<PriceLevelDTO> findByPrice(Integer price,Pageable pageable);
}
