package com.mimuw.module;

import com.mimuw.option.Option;
import lombok.Data;

import java.util.List;

@Data
public class Module {

    public static final String TABLE_NAME = "module";
    public static final String OPTION_TABLE_NAME = "option";

    private Long id;

    private Long productId;

    private String name;

    private List<Option> options;
}
