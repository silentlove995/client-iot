package com.iot.client.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author datdv
 */
@Entity
@Table(name = "price_level")
@Getter
@Setter
public class PriceLevelEntity extends BaseEntity {

    @Column(name = "price")
    private Integer price;

    @Column(name = "time_from")
    private Integer timeFrom;

    @Column(name = "time_to")
    private Integer timeTo;

    @Column(name = "time")
    private Integer time;

    @Column(name = "type")
    private String type;

    @ManyToMany(mappedBy = "priceLevels")
    private List<DeviceEntity> devices;

    @OneToMany(mappedBy = "priceLevel" , fetch = FetchType.LAZY)
    private List<com.iot.client.entity.HistoryDeviceEntity> historyDevices;

}
