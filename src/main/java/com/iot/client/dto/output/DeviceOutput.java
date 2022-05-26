package com.iot.client.dto.output;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iot.client.dto.PriceLevelView;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author datdv
 */
@Getter
@Setter
public class DeviceOutput {

    private Long id;
    private String name;
    private String code;
    private String qrCode;
    private Integer status;
    private Integer usages;
    private Integer statusTransaction;

    @JsonIgnore
    private String timeFormTo;

    private String fullName;
    private String username;
    private Integer statusUser;
    private String email;
    private String numberPhone;
    private Integer wallet;
    private Long userIdCreated;
    private String focusRole;
    private List<PriceLevelView> levelViews;

    public DeviceOutput() {
    }

    public DeviceOutput(Long id, String name, String code, String qrCode, Integer status, Integer usages, Integer statusTransaction, String timeFormTo, String fullName, String username, Integer statusUser, String email, String numberPhone, Integer wallet, Long userIdCreated, String focusRole) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.qrCode = qrCode;
        this.status = status;
        this.usages = usages;
        this.statusTransaction = statusTransaction;
        this.timeFormTo = timeFormTo;
        this.fullName = fullName;
        this.username = username;
        this.statusUser = statusUser;
        this.email = email;
        this.numberPhone = numberPhone;
        this.wallet = wallet;
        this.userIdCreated = userIdCreated;
        this.focusRole = focusRole;
    }
}
