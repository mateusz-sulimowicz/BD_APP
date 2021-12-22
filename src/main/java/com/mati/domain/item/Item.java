package com.mati.domain.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Data
public class Item {

    public static final String TABLE_NAME = "item";

    private Long id;

    private String name;

}
