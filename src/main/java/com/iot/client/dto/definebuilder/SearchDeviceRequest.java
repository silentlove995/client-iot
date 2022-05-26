package com.iot.client.dto.definebuilder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchDeviceRequest {
    private String name;
    private String code;
    private String qrCode;
    private Integer usages;
    private Integer status;


    private String fullName;
    private String username;
    private String email;
    private String numberPhone;
    private Integer wallet;
}
