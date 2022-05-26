package com.iot.client.dto.output.sqlcustom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryPaymentOutPut {

    private Date createdate;
    private Date modifiledate;
    private String modifiledy;
    private String paymenttype;
    private Long total;

    private String fullName;
    private String username;
    private String email;
    private String numberphone;
}
