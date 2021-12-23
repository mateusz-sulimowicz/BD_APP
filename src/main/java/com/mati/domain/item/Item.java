package com.mati.domain.item;

import lombok.Data;

@Data
public class Item {

    public static final String TABLE_NAME = "item";

    private Long id;

    private String name;

}
