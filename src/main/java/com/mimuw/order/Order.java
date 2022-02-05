package com.mimuw.order;

import com.mimuw.order.config.OrderConfig;
import com.mimuw.product.Product;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class Order {

    public static final String TABLE_NAME = "product_order";
    public static final String CONFIG_TABLE_NAME = "config";

    private Long id;

    private Product product;

    private Long quantity;

    private BigDecimal value;

    private Date orderDate;

    List<OrderConfig> orderConfigs;

}
