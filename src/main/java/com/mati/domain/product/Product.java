package com.mati.domain.product;

import com.mati.domain.module.Module;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = Product.TABLE_NAME)
@Getter
@Setter
@ToString
public class Product {

    public static final String TABLE_NAME = "product";
    public static final String RECIPE_TABLE_NAME = "recipe";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "base_price", nullable = false)
    private BigDecimal basePrice;

    @OneToMany
    @JoinTable(name = RECIPE_TABLE_NAME,
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "module_id"))
    @ToString.Exclude
    private List<Module> modules;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
