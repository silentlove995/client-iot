package com.iot.client.dto.output.sqlcustom;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author datdv
 */
@Getter
@Setter
public class UserOutPut {
    private Long id;
    private Date createdDate;
    private Date modifiedDate;
    private String createdBy;
    private String modifiedBy;
    private String fullName;
    private String username;
    private Integer status;
    private String email;
    private String numberPhone;
    private Integer wallet;

    public UserOutPut() {
    }

    public UserOutPut(Long id, Date createdDate, Date modifiedDate, String createdBy, String modifiedBy, String fullName, String username, Integer status, String email, String numberPhone, Integer wallet) {
        this.id = id;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.fullName = fullName;
        this.username = username;
        this.status = status;
        this.email = email;
        this.numberPhone = numberPhone;
        this.wallet = wallet;
    }
}
