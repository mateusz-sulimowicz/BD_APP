package com.mimuw.product;

import com.mimuw.module.Module;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Product {

    public static final String TABLE_NAME = "product";
    public static final String RECIPE_TABLE_NAME = "recipe";

    private Long id;

    private String name;

    private BigDecimal basePrice;

    private List<Module> modules;

}
