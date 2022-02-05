package com.mimuw.domain.order;

import com.mimuw.domain.order.config.OrderConfig;
import com.mimuw.domain.order.config.OrderConfigDAO;
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
public class OrderDAO {

    static private final String FIND_ALL = "select * from product_order o";

    static private final String FIND_BY_ID = "select * from product_order o where o.id = :orderId";

    static private final String CREATE = "insert into product_order (product_id, quantity, value)" +
            "values (:productId, :quantity, :value)";

    static private final String DELETE = "delete from product_order where id = :orderId";

    NamedParameterJdbcTemplate jdbcTemplate;

    OrderRowMapper rowMapper;

    OrderConfigDAO orderConfigDAO;

    public OrderDAO(NamedParameterJdbcTemplate jdbcTemplate,
                    OrderRowMapper rowMapper,
                    OrderConfigDAO orderConfigDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.orderConfigDAO = orderConfigDAO;
    }

    public List<Order> findAll() {
        return jdbcTemplate.query(FIND_ALL, rowMapper);
    }

    public Optional<Order> findById(Long id) {
        SqlParameterSource parameters = new MapSqlParameterSource("orderId", id);

        try {
            Order order = jdbcTemplate.queryForObject(FIND_BY_ID, parameters, rowMapper);
            return Optional.of(order);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Order create(Order order) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("productId", order.getProduct().getId())
                .addValue("quantity", order.getQuantity())
                .addValue("value", order.getValue());

        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(CREATE, parameters, holder);

        Integer key = (Integer) holder.getKeys().get("id");

        order.setId(key.longValue());

        for (OrderConfig c : order.getOrderConfigs()) {
            c.setOrderId(order.getId());
            orderConfigDAO.create(c);
        }

        return order;
    }

    public void deleteById(Long id) {
        orderConfigDAO.deleteByOrderId(id);

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("orderId", id);

        jdbcTemplate.update(DELETE, parameters);
    }

}
