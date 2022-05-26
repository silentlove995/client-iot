package com.iot.client.entity;

import com.iot.client.dto.output.sqlcustom.HistoryPaymentOutPut;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author datdv
 */
@Entity
@Table(name = "history_payment")
@Getter
@Setter
@SqlResultSetMapping(name = "findAll" , classes = {
        @ConstructorResult(targetClass = HistoryPaymentOutPut.class ,
                columns = {
                        @ColumnResult(name = "history_payment_createddate", type = Date.class),
                        @ColumnResult(name = "history_payment_modifieddate", type = Date.class),
                        @ColumnResult(name = "history_payment_modifiedby", type = String.class),
                        @ColumnResult(name = "history_payment_payment_type", type = String.class),
                        @ColumnResult(name = "history_payment_total", type = Long.class),
                        @ColumnResult(name = "users_fullname", type = String.class),
                        @ColumnResult(name = "users_username", type = String.class),
                        @ColumnResult(name = "users_email", type = String.class),
                        @ColumnResult(name = "users_numberphone", type = String.class),

                })
})
public class HistoryPaymentEntity extends BaseEntity {

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "status")
    private Integer status;

    @Column(name = "total")
    private Long total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
