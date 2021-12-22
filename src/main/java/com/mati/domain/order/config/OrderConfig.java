package com.mati.domain.order.config;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "config")
public class OrderConfig {

    public static final String TABLE_NAME = "config";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "module_id", nullable = false)
    private Long moduleId;

    @Column(name = "item_id", nullable = false)
    private Long itemId;

}
