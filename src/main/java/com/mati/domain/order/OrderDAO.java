package com.mati.domain.order;

import com.mati.domain.product.Product;
import com.mati.domain.product.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderDAO {

    static private final String FIND_ALL = "select * from product_order o";

    static private final String FIND_BY_ID = "select * from product_order o where o.id = :orderId";

    NamedParameterJdbcTemplate jdbcTemplate;

    OrderRowMapper rowMapper;

    @Autowired
    public OrderDAO(NamedParameterJdbcTemplate jdbcTemplate, OrderRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
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


}
