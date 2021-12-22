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

}
