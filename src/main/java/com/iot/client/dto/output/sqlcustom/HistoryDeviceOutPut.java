package com.iot.client.dto.output.sqlcustom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author datdv
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryDeviceOutPut {
    private Long historyDeviceId;
    private String historyDeleted;
    private Date startDate;
    private Date endDate;
    private Integer totalPayment;
    private String totalTime;
    private Long userId;
    private String userFullNameUse;
    private String userPhoneNumberUse;
    private String userEmailUse;
    private Long deviceId;
    private String deviceCode;
    private String deviceName;
    private Long priceLevelId;
    private Integer priceLevelPrice;
    private Integer priceLevelTimeForm;
    private Integer priceLevelTimeTo;
    private String userAdminDeviceName;
}
