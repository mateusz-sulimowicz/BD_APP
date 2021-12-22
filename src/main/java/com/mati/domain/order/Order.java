package com.mati.domain.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mati.domain.order.config.OrderConfig;
import com.mati.domain.product.Product;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
