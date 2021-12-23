package com.mati.domain.order.config;

import lombok.Data;

@Data
public class OrderConfig {

    public static final String TABLE_NAME = "config";

    private Long id;

    private Long orderId;

    private Long moduleId;

    private Long itemId;

}
