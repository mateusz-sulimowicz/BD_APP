package com.mati.domain.product;

import com.mati.domain.module.Module;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Data
public class Product {

    public static final String TABLE_NAME = "product";
    public static final String RECIPE_TABLE_NAME = "recipe";

    private Long id;

    private String name;

    private BigDecimal basePrice;

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
