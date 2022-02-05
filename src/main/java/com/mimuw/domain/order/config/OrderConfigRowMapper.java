package com.mimuw.domain.order.config;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class OrderConfigRowMapper implements RowMapper<OrderConfig> {

    @Override
    public OrderConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderConfig c = new OrderConfig();

        c.setItemId(rs.getLong("item_id"));
        c.setOrderId(rs.getLong("order_id"));
        c.setModuleId(rs.getLong("module_id"));

        return c;
    }
}
