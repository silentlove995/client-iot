package com.iot.client.dto.shortdto;

import com.iot.client.entity.PriceLevelEntity;

import java.util.Comparator;

public class SortByTimePriceLevel implements Comparator<PriceLevelEntity> {

    @Override
    public int compare(PriceLevelEntity o1, PriceLevelEntity o2) {
        return Integer.parseInt(o1.getPrice() - o2.getPrice() + "");
    }

}
