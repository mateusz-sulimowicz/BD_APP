package com.mati.domain.module;

import com.mati.domain.item.Item;
import com.mati.domain.option.Option;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = Module.TABLE_NAME)
@Getter
@Setter
@ToString
public class Module {

    public static final String TABLE_NAME = "module";
    public static final String OPTION_TABLE_NAME = "option";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany
    @JoinColumn(name = "module_id")
    @ToString.Exclude
    private List<Option> options;
}
