package com.mimuw.domain.order.config;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class OrderConfigDAO {

    static private final String FIND_ALL = "select * from product_order o";

    static private final String FIND_ALL_BY_ORDER_ID = "select * from config c where c.order_id = :orderId";

    static private final String CREATE = "insert into config(order_id, module_id, item_id) " +
            "values (:orderId, :moduleId, :itemId)";

    static private final String DELETE_ALL_BY_ORDER_ID = "delete from config where order_id = :orderId";

    NamedParameterJdbcTemplate jdbcTemplate;

    OrderConfigRowMapper rowMapper;

    public OrderConfigDAO(NamedParameterJdbcTemplate jdbcTemplate, OrderConfigRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    public List<OrderConfig> findAllByOrderId(Long id) {
        SqlParameterSource parameters = new MapSqlParameterSource("orderId", id);
        return jdbcTemplate.query(FIND_ALL_BY_ORDER_ID, parameters, rowMapper);
    }


    public OrderConfig create(OrderConfig orderConfig) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("orderId", orderConfig.getOrderId())
                .addValue("moduleId", orderConfig.getModuleId())
                .addValue("itemId", orderConfig.getItemId());

        jdbcTemplate.update(CREATE, parameters);

        return orderConfig;
    }

    public void deleteByOrderId(Long id) {
        SqlParameterSource parameters = new MapSqlParameterSource("orderId", id);

        jdbcTemplate.update(DELETE_ALL_BY_ORDER_ID, parameters);
    }


}
