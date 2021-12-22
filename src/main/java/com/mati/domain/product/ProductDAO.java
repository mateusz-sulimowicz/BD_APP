package com.mati.domain.product;

import com.mati.domain.item.Item;
import com.mati.domain.item.ItemRowMapper;
import com.mati.domain.option.Option;
import com.mati.domain.option.OptionRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductDAO {

    static private final String FIND_ALL = "select * from product p";

    static private final String FIND_BY_ID = "select * from product p where p.id = :productId";

    NamedParameterJdbcTemplate jdbcTemplate;

    ProductRowMapper rowMapper;

    @Autowired
    public ProductDAO(NamedParameterJdbcTemplate jdbcTemplate, ProductRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    public List<Product> findAll() {
        return jdbcTemplate.query(FIND_ALL, rowMapper);
    }

    public Optional<Product> findById(Long id) {
        SqlParameterSource parameters = new MapSqlParameterSource("productId", id);

        try {
            Product foundProduct = jdbcTemplate.queryForObject(FIND_BY_ID, parameters, rowMapper);
            return Optional.of(foundProduct);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }


}
