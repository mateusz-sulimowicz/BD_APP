package com.mimuw.domain.item;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemRowMapper implements RowMapper<Item> {

    @Override
    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
        Item item = new Item();

        item.setId(rs.getLong("ID"));
        item.setName(rs.getString("NAME"));

        return item;
    }

}
