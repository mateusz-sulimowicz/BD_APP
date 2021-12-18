package com.mati.domain.module;

import com.mati.domain.item.Item;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = Module.TABLE_NAME)
public class Module {

    public static final String TABLE_NAME = "module";
    public static final String OPTION_TABLE_NAME = "option";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany
    @JoinTable(name = OPTION_TABLE_NAME,
            joinColumns = @JoinColumn(name = "module_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items;

    public Module(String name) {
        this.name = name;
    }

    public Module() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
