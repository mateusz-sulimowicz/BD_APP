package com.mimuw.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ItemDAO {

    static private final String FIND_ALL = "select * from item i";

    static private final String FIND_BY_ID = "select * from item i where i.id = :item_id";

    static private final String FIND_BY_CODE = "select * from item i where i.name = :item_code";

    static private final String FIND_BY_ORDER_AND_MODULE = "select i.* from item i " +
            "join config c on i.id = c.item_id " +
            "where c.order_id = :order_id and c.module_id = :module_id";

    static private final String CREATE = "insert into item (name) values (:name)";

    static private final String DELETE = "delete from item i where i.id = :item_id";

    static private final String UPDATE = "update item set name = :name where id = :id";

    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public ItemDAO(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Item> findAll() {
        return jdbcTemplate.query(FIND_ALL, new ItemRowMapper());
    }

    public Optional<Item> findById(Long id) {
        SqlParameterSource parameters = new MapSqlParameterSource("item_id", id);

        try {
            Item foundItem = jdbcTemplate.queryForObject(FIND_BY_ID, parameters, new ItemRowMapper());
            return Optional.of(foundItem);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Optional<Item> findByCode(String code) {
        SqlParameterSource parameters = new MapSqlParameterSource("item_code", code);

        try {
            Item foundItem = jdbcTemplate.queryForObject(FIND_BY_CODE, parameters, new ItemRowMapper());
            return Optional.of(foundItem);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }

    public Optional<Item> findByOrderAndModule(Long orderId, Long moduleId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("order_id", orderId)
                .addValue("module_id", moduleId);

        try {
            Item foundItem = jdbcTemplate.queryForObject(FIND_BY_ORDER_AND_MODULE, parameters, new ItemRowMapper());
            return Optional.of(foundItem);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Item create(Item item) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", item.getName());

        jdbcTemplate.update(CREATE, parameters);

        return item;
    }

    public Item update(Item item) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", item.getId())
                .addValue("name", item.getName());

        jdbcTemplate.update(UPDATE, parameters);

        return item;
    }


    void deleteById(Long id) {
        SqlParameterSource parameters = new MapSqlParameterSource("item_id", id);

        jdbcTemplate.update(DELETE, parameters);
    }

}
