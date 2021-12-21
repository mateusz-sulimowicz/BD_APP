package com.mati.domain.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mati.domain.product.Product;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = Order.TABLE_NAME)
@DynamicInsert
@Getter
@Setter
@ToString
public class Order {

    public static final String TABLE_NAME = "product_order";
    public static final String CONFIG_TABLE_NAME = "config";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "value")
    private BigDecimal value;


    @Column(name = "order_date")
    private LocalDate orderDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Order order = (Order) o;
        return id != null && Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
