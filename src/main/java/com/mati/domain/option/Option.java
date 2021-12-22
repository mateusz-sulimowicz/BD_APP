package com.mati.domain.option;

import com.mati.domain.item.Item;
import com.mati.domain.module.Module;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Data
public class Option {

    public static final String TABLE_NAME = "option";

    private Long moduleId;

    private BigDecimal price;

    private Item item;
}
