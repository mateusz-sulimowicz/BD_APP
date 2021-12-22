package com.mati.domain.order.config;

import com.mati.domain.order.Order;
import com.mati.domain.order.OrderRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class OrderConfigDAO {

    static private final String FIND_ALL = "select * from product_order o";

    static private final String FIND_ALL_BY_ORDER_ID = "select * from config c where c.order_id = :orderId";

    static private final String CREATE = "insert into config(order_id, module_id, item_id) " +
            "values (:orderId, :moduleId, :itemId)";

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

        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(CREATE, parameters, holder);

        Integer key = (Integer) holder.getKeys().get("id");
        orderConfig.setId(key.longValue());

        return orderConfig;
    }

    /*

    public Optional<Order> findById(Long id) {
        SqlParameterSource parameters = new MapSqlParameterSource("orderId", id);

        try {
            Order order = jdbcTemplate.queryForObject(FIND_BY_ID, parameters, rowMapper);
            return Optional.of(order);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }*/


}
