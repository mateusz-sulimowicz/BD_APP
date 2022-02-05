package com.mimuw.domain.product;

import com.mimuw.domain.module.Module;
import com.mimuw.domain.module.ModuleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class ProductRowMapper implements RowMapper<Product> {

    ModuleDAO moduleDAO;

    @Autowired
    public ProductRowMapper(ModuleDAO moduleDAO) {
        this.moduleDAO = moduleDAO;
    }

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setName(rs.getString("name"));
        product.setId(rs.getLong("id"));
        product.setBasePrice(rs.getBigDecimal("base_price"));

        List<Module> modules = moduleDAO.findAllByProductId(product.getId());
        product.setModules(modules);

        return product;
    }
}
