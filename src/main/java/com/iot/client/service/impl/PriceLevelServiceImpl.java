package com.iot.client.service.impl;

import com.iot.client.converter.Converter;
import com.iot.client.dto.PriceLevelDTO;
import com.iot.client.entity.PriceLevelEntity;
import com.iot.client.repository.PriceLevelRepository;
import com.iot.client.service.PriceLevelService;
import com.iot.client.utils.CommonUtils;
import com.iot.client.utils.error.CustomException;
import com.iot.client.utils.response.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author datdv
 */
@Service
public class PriceLevelServiceImpl implements PriceLevelService {

    @Autowired
    private PriceLevelRepository priceLevelRepository;


    @Override
    @Transactional
    public PriceLevelDTO save(PriceLevelDTO input) {
        PriceLevelEntity entity = Converter.toModel(input , PriceLevelEntity.class);
        entity = priceLevelRepository.save(entity);
        return Converter.toModel(entity , PriceLevelDTO.class);
    }

    @Override
    @Transactional
    public PriceLevelDTO update(Long id , PriceLevelDTO update) {
        update.setId(id);
        PriceLevelEntity entity = priceLevelRepository.findOne(update.getId());
        if (Objects.isNull(entity)) {
            throw new CustomException("price level not found" , CommonUtils.putError("PriceLevelDTO" , ""));
        }
        PriceLevelEntity priceLevelEntity = Converter.toModel(update , PriceLevelEntity.class);
        priceLevelEntity = priceLevelRepository.save(priceLevelEntity);
        return Converter.toModel(priceLevelEntity , PriceLevelDTO.class);
    }

    @Override
    public PriceLevelDTO findOne(Long id) {
        PriceLevelEntity entity = priceLevelRepository.findOne(id);
        if (Objects.isNull(entity)) {
            throw new CustomException("price level not found" , CommonUtils.putError("PriceLevelEntity" , ""));
        }
        return Converter.toModel(entity , PriceLevelDTO.class);
    }

    @Override
    @Transactional
    public void delete(List<Long> ids) {
        ids.forEach(priceLevelRepository::deleteById);
    }

    @Override
    public PageList<PriceLevelDTO> findAllPriceLevel(Pageable pageable) {
        List<PriceLevelEntity> entities = priceLevelRepository.findAll(pageable).getContent();
        List<PriceLevelDTO> priceLevels = Converter.toList(entities, PriceLevelDTO.class);
        Long count = priceLevelRepository.countPriceLevelEntities();
        return PageList.<PriceLevelDTO>builder()
                .list(priceLevels)
                .currentPage(pageable.getPageNumber() + 1)
                .total(count)
                .pageSize(pageable.getPageSize())
                .success(true)
                .totalPage((int) Math.ceil((double) Integer.parseInt(count.toString()) / pageable.getPageSize()))
                .build();
    }

    @Override
    public List<PriceLevelDTO> findAll() {
        List<PriceLevelEntity> entities = priceLevelRepository.findAll();
        return Converter.toList(entities, PriceLevelDTO.class);
    }


    @Override
    public List<PriceLevelDTO> findByType(String type) {
        List<PriceLevelEntity> priceLevels = priceLevelRepository.findByType(type);
        return Converter.toList(priceLevels, PriceLevelDTO.class);
    }

    @Override
    public PageList<PriceLevelDTO> findByPrice(Integer price, Pageable pageable) {
        List<PriceLevelDTO> priceLevels = Converter.toList(priceLevelRepository.findByPrice(price), PriceLevelDTO.class);
        Long count = priceLevelRepository.countPriceLevelEntities();
        return PageList.<PriceLevelDTO>builder()
                .list(priceLevels)
                .currentPage(pageable.getPageNumber() + 1)
                .total(count)
                .pageSize(pageable.getPageSize())
                .success(true)
                .totalPage((int) Math.ceil((double) Integer.parseInt(count.toString()) / pageable.getPageSize()))
                .build();
    }
}
