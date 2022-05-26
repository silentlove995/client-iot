package com.iot.client.builder;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class HistoryPaymentBuilder {
    private String createdDateFrom;
    private String createdDateTo;
    private String paymentType;
    private Long totalFrom;
    private Long totalTo;

    private String fullName;
    private String username;
    private String email;
    private String numberPhone;
}
