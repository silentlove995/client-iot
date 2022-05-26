package com.iot.client.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author datdv
 */
@Entity
@Table(name = "system_card")
@Getter
@Setter
public class SystemCardEntity extends BaseEntity {

    @Column(name = "code_card")
    private String codeCard;

    @Column(name = "price")
    private Integer price;

    @Column(name = "last_code")
    private Integer lastCode;

    @Column(name = "status")
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
