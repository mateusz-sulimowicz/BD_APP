package com.mati.domain.product;

import com.mati.domain.item.Item;
import com.mati.domain.item.ItemDAO;
import com.mati.domain.module.Module;
import com.mati.domain.module.ModuleDAO;
import com.mati.domain.option.Option;
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

        List<Module> modules = moduleDAO.findAllByProductId(product.getId());
        product.setModules(modules);

        return product;
    }
}
