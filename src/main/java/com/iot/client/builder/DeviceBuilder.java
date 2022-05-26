package com.iot.client.builder;

import lombok.*;

import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeviceBuilder {
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
