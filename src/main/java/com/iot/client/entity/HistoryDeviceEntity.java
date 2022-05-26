package com.iot.client.entity;

import com.iot.client.dto.output.sqlcustom.HistoryDeviceOutPut;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author datdv
 */
@Entity
@Table(name = "history_device")
@Getter
@Setter
@SqlResultSetMapping(name = "findAllHistoryDeviceByCondition" , classes = {
        @ConstructorResult(targetClass = HistoryDeviceOutPut.class ,
                columns = {
                        @ColumnResult(name = "history_device_id", type = Long.class),
                        @ColumnResult(name = "history_deleted", type = String.class),
                        @ColumnResult(name = "start_date", type = Date.class),
                        @ColumnResult(name = "end_date", type = Date.class),
                        @ColumnResult(name = "total_payment", type = Integer.class),
                        @ColumnResult(name = "total_time", type = String.class),
                        @ColumnResult(name = "user_id", type = Long.class),
                        @ColumnResult(name = "user_full_name_use", type = String.class),
                        @ColumnResult(name = "user_phone_number_use", type = String.class),
                        @ColumnResult(name = "user_email_use", type = String.class),
                        @ColumnResult(name = "device_id", type = Long.class),
                        @ColumnResult(name = "device_code", type = String.class),
                        @ColumnResult(name = "device_name", type = String.class),
                        @ColumnResult(name = "price_level_id", type = Long.class),
                        @ColumnResult(name = "price_level_price", type = Integer.class),
                        @ColumnResult(name = "price_level_time_form", type = Integer.class),
                        @ColumnResult(name = "price_level_time_to", type = Integer.class),
                        @ColumnResult(name = "user_admin_device_name", type = String.class)
                })
})
public class HistoryDeviceEntity extends BaseEntity {

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "total_time")
    private String totalTime;

    @Column(name = "total_payment")
    private Integer totalPayment;

    @Column(name = "status")
    private Integer status;

    @Column(name = "deleted")
    private Integer deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    private DeviceEntity device;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "price_level_id")
    private PriceLevelEntity priceLevel;
}
