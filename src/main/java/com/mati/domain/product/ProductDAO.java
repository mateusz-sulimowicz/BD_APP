package com.mati.domain.product;

import com.mati.domain.item.Item;
import com.mati.domain.item.ItemRowMapper;
import com.mati.domain.module.Module;
import com.mati.domain.module.ModuleDAO;
import com.mati.domain.option.Option;
import com.mati.domain.option.OptionRowMapper;
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
public class ProductDAO {

    static private final String FIND_ALL = "select * from product p";

    static private final String FIND_BY_ID = "select * from product p where p.id = :productId";

    static private final String DELETE = "delete from product where id = :productId";

    NamedParameterJdbcTemplate jdbcTemplate;

    ProductRowMapper rowMapper;

    ModuleDAO moduleDAO;

    public ProductDAO(NamedParameterJdbcTemplate jdbcTemplate,
                      ProductRowMapper rowMapper,
                      ModuleDAO moduleDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.moduleDAO = moduleDAO;
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

    public void deleteById(Long id) {
        for (Module m : moduleDAO.findAllByProductId(id)) {
            moduleDAO.deleteById(m.getId());
        }

        SqlParameterSource parameters = new MapSqlParameterSource("productId", id);

        jdbcTemplate.update(DELETE, parameters);
    }

}
