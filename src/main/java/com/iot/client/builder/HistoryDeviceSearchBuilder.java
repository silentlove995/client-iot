package com.iot.client.builder;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HistoryDeviceSearchBuilder {
    private String startDate;
    private String startToDate;
    private String endDate;
    private String endToDate;
    private String totalTime;
    private Integer totalPayment;
    private Integer status;
    private Integer deleted;

    private String fullName;
    private String username;
    private String email;
    private String numberPhone;

    private String name;
    private String code;
    private String qrCode;
    private Integer usages;

    private Integer price;
    private Integer timeForm;
    private Integer timeTo;
    private String type;
}

