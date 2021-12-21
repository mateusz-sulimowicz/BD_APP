package com.mati.domain.option;

import com.mati.domain.item.Item;
import com.mati.domain.module.Module;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = Option.TABLE_NAME)
@Getter
@Setter
public class Option {

    public static final String TABLE_NAME = "option";

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "module_id")
    private Long moduleId;

    @Column
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Option option = (Option) o;
        return Objects.equals(id, option.id) && Objects.equals(item, option.item) && Objects.equals(moduleId, option.moduleId) && Objects.equals(price, option.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, item, moduleId, price);
    }
}
