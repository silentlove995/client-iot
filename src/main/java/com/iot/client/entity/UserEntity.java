package com.iot.client.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iot.client.dto.output.sqlcustom.UserOutPut;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author datdv
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@SqlResultSetMapping(name = "findAllUserByCondition" , classes = {
        @ConstructorResult(targetClass = UserOutPut.class ,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "createddate", type = Date.class),
                        @ColumnResult(name = "modifieddate", type = Date.class),
                        @ColumnResult(name = "createdby", type = String.class),
                        @ColumnResult(name = "modifiedby", type = String.class),
                        @ColumnResult(name = "fullname", type = String.class),
                        @ColumnResult(name = "username", type = String.class),
                        @ColumnResult(name = "status", type = Integer.class),
                        @ColumnResult(name = "email", type = String.class),
                        @ColumnResult(name = "numberphone", type = String.class),
                        @ColumnResult(name = "wallet", type = Integer.class)
                })
})
public class UserEntity extends com.iot.client.entity.BaseEntity {

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "username")
    private String username;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private Integer status;

    @Column(name = "email")
    private String email;

    @Column(name = "numberphone")
    private String numberPhone;

    @Column(name = "wallet")
    private Integer wallet;

    @Column(name = "pass_word_demo")
    private String passwordDemo;

    @Column(name = "user_id_created")
    private Long userIdCreated;

    @Column(name = "focus_role")
    private String focusRole;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role" , joinColumns = @JoinColumn(name = "user_id") ,
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<com.iot.client.entity.RoleEntity> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user" , fetch = FetchType.LAZY)
    private List<com.iot.client.entity.DeviceEntity> devices = new ArrayList<>();

    @OneToMany(mappedBy = "user" , fetch = FetchType.LAZY)
    private List<com.iot.client.entity.SystemCardEntity> systemCards = new ArrayList<>();

    @OneToMany(mappedBy = "user" , fetch = FetchType.LAZY)
    private List<com.iot.client.entity.HistoryDeviceEntity> historyDevices = new ArrayList<>();

    @OneToMany(mappedBy = "user" , fetch = FetchType.LAZY)
    private List<com.iot.client.entity.HistoryPaymentEntity> historyPayments = new ArrayList<>();

}
