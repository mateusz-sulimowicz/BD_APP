package com.mati.domain.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ItemDAO {

    static private final String FIND_ALL = "select * from item i";

    static private final String FIND_BY_ID = "select * from item i where i.id = :item_id";

    static private final String FIND_BY_ORDER_AND_MODULE = "select i.* from item i " +
            "join config c on i.id = c.item_id " +
            "where c.order_id = :order_id and c.module_id = :module_id";

    static private final String CREATE = "insert into item (id, name) values (:id, :name)";

    static private final String DELETE = "delete from item i where i.id = :item_id";

    static private final String UPDATE = "update item set name = :name where id = :id";

    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public ItemDAO(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    List<Item> findAll() {
        return jdbcTemplate.query(FIND_ALL, new ItemRowMapper());
    }

    Optional<Item> findById(Long id) {
        SqlParameterSource parameters = new MapSqlParameterSource("item_id", id);

        try {
            Item foundItem = jdbcTemplate.queryForObject(FIND_BY_ID, parameters, new ItemRowMapper());
            return Optional.of(foundItem);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    Optional<Item> findByOrderAndModule(Long orderId, Long moduleId) {
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
                .addValue("id", item.getId())
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
