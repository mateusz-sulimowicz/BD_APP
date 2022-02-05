package com.mimuw.domain.order.config;

import lombok.Data;

@Data
public class OrderConfig {

    public static final String TABLE_NAME = "config";

    private Long orderId;

    private Long moduleId;

    private Long itemId;

}
