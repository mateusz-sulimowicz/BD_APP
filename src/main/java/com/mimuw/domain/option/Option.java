package com.mimuw.domain.option;

import com.mimuw.domain.item.Item;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Option {

    public static final String TABLE_NAME = "option";

    private Long moduleId;

    private BigDecimal price;

    private Item item;
}
