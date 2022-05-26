package com.iot.client.entity;

import com.iot.client.dto.output.DeviceOutput;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author datdv
 */
@Entity
@Table(name = "devices")
@Getter
@Setter
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "findAllDevice", classes = {
                @ConstructorResult(targetClass = DeviceOutput.class,
                        columns = {
                                @ColumnResult(name = "device_id", type = Long.class),
                                @ColumnResult(name = "device_name", type = String.class),
                                @ColumnResult(name = "device_code", type = String.class),
                                @ColumnResult(name = "device_qr_code", type = String.class),
                                @ColumnResult(name = "device_status", type = Integer.class),
                                @ColumnResult(name = "device_usages", type = Integer.class),
                                @ColumnResult(name = "device_status_transaction", type = Integer.class),
                                @ColumnResult(name = "price_level_view_time_from_to", type = String.class),
                                @ColumnResult(name = "users_fullname", type = String.class),
                                @ColumnResult(name = "users_username", type = String.class),
                                @ColumnResult(name = "users_status", type = Integer.class),
                                @ColumnResult(name = "users_email", type = String.class),
                                @ColumnResult(name = "users_numberphone", type = String.class),
                                @ColumnResult(name = "users_wallet", type = Integer.class),
                                @ColumnResult(name = "user_id_created", type = Long.class),
                                @ColumnResult(name = "user_focus_role", type = String.class)

                        })
        }),
        @SqlResultSetMapping(name = "findById", classes = {
                @ConstructorResult(targetClass = DeviceOutput.class,
                        columns = {
                                @ColumnResult(name = "device_id", type = Long.class),
                                @ColumnResult(name = "device_name", type = String.class),
                                @ColumnResult(name = "device_code", type = String.class),
                                @ColumnResult(name = "device_qr_code", type = String.class),
                                @ColumnResult(name = "device_status", type = Integer.class),
                                @ColumnResult(name = "device_usages", type = Integer.class),
                                @ColumnResult(name = "device_status_transaction", type = Integer.class),
                                @ColumnResult(name = "price_level_view_time_from_to", type = String.class),
                                @ColumnResult(name = "users_fullname", type = String.class),
                                @ColumnResult(name = "users_username", type = String.class),
                                @ColumnResult(name = "users_status", type = Integer.class),
                                @ColumnResult(name = "users_email", type = String.class),
                                @ColumnResult(name = "users_numberphone", type = String.class),
                                @ColumnResult(name = "users_wallet", type = Integer.class),
                                @ColumnResult(name = "user_id_created", type = Long.class),
                                @ColumnResult(name = "user_focus_role", type = String.class)

                        })
        })
})
public class DeviceEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "qr_code")
    private String qrCode;

    @Column(name = "status")
    private Integer status;

    @Column(name = "usages")
    private Integer usages;

    @Column(name = "status_transaction")
    private Integer statusTransaction;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "device_price" , joinColumns = @JoinColumn(name = "device_id") ,
            inverseJoinColumns = @JoinColumn(name = "price_id"))
    private List<com.iot.client.entity.PriceLevelEntity> priceLevels = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "device" , fetch = FetchType.LAZY)
    private List<com.iot.client.entity.TransactionEntity> transactions = new ArrayList<>();

    @OneToMany(mappedBy = "device" , fetch = FetchType.LAZY)
    private List<com.iot.client.entity.UUIDTokenDeviceEntity> uuidTokenDevices = new ArrayList<>();

    @OneToMany(mappedBy = "device" , fetch = FetchType.LAZY)
    private List<com.iot.client.entity.HistoryDeviceEntity> historyDevices = new ArrayList<>();
}
