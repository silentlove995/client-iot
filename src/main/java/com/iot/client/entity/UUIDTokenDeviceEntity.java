package com.iot.client.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "uuid_token_device")
@Getter
@Setter
public class UUIDTokenDeviceEntity extends BaseEntity {

    @Column(name = "uuid_token")
    private String uuidToken;

    @Column(name = "number_phone_user")
    private String numberPhoneUser;

    @Column(name = "is_deleted")
    private int isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    private DeviceEntity device;
}
