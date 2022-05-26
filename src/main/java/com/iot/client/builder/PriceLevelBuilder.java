package com.iot.client.builder;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PriceLevelBuilder {
    private String type;
    private Integer price;
}
