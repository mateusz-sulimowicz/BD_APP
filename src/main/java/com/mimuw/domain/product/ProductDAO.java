package com.mimuw.domain.product;

import com.mimuw.domain.module.Module;
import com.mimuw.domain.module.ModuleDAO;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ProductDAO {

    static private final String FIND_ALL = "select * from product p";

    static private final String FIND_BY_ID = "select * from product p where p.id = :productId";

    static private final String FIND_BY_NAME = "select * from product p where p.name = :name";

    static private final String CREATE = "insert into product (name, base_price) values (:name, :basePrice)";

    static private final String UPDATE = "update product set name = :name, base_price = :basePrice where id = :id";

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

    public Product create(Product product) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", product.getName())
                .addValue("basePrice", product.getBasePrice());

        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(CREATE, parameters, holder);

        Integer key = (Integer) holder.getKeys().get("id");
        product.setId(key.longValue());
        return product;
    }

    public Product update(Product product) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", product.getId())
                .addValue("name", product.getName())
                .addValue("basePrice", product.getBasePrice());

        jdbcTemplate.update(UPDATE, parameters);

        return product;
    }

    public void deleteById(Long id) {
        for (Module m : moduleDAO.findAllByProductId(id)) {
            moduleDAO.deleteById(m.getId());
        }

        SqlParameterSource parameters = new MapSqlParameterSource("productId", id);

        jdbcTemplate.update(DELETE, parameters);
    }

    public Product createCopyOf(Product product) {
        Product productCopy = new Product();
        productCopy.setName(product.getName() + "_1");
        productCopy.setBasePrice(product.getBasePrice());

        productCopy = create(productCopy);

        System.out.println(productCopy);

        List<Module> productCopyModules = new ArrayList<>();
        for (var module : findById(product.getId()).get().getModules()) {
            productCopyModules.add(moduleDAO.createCopyOf(module, productCopy.getId()));
        }
        productCopy.setModules(productCopyModules);
        return productCopy;
    }


}
