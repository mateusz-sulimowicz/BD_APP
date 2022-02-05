package com.mimuw.option;

import com.mimuw.item.Item;
import com.mimuw.item.ItemDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class OptionRowMapper implements RowMapper<Option> {

    ItemDAO itemDAO;

    @Autowired
    public OptionRowMapper(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    @Override
    public Option mapRow(ResultSet rs, int rowNum) throws SQLException {
        Option option = new Option();
        Item item = itemDAO
                .findById(rs.getLong("item_id"))
                .orElseThrow(RuntimeException::new);;

        option.setPrice(rs.getBigDecimal("price"));
        option.setModuleId(rs.getLong("module_id"));
        option.setItem(item);

        return option;
    }
}
