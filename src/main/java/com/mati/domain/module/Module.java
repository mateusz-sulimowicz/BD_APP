package com.mati.domain.module;

import com.mati.domain.item.Item;
import com.mati.domain.option.Option;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
public class Module {

    public static final String TABLE_NAME = "module";
    public static final String OPTION_TABLE_NAME = "option";

    private Long id;

    private String name;

    private List<Option> options;
}
