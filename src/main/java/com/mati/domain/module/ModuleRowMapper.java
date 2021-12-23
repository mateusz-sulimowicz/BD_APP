package com.mati.domain.module;

import com.mati.domain.item.Item;
import com.mati.domain.item.ItemDAO;
import com.mati.domain.option.Option;
import com.mati.domain.option.OptionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class ModuleRowMapper implements RowMapper<Module> {

    OptionDAO optionDAO;

    @Autowired
    public ModuleRowMapper(OptionDAO optionDAO) {
        this.optionDAO = optionDAO;
    }

    @Override
    public Module mapRow(ResultSet rs, int rowNum) throws SQLException {
        Module module = new Module();

       module.setId(rs.getLong("ID"));
       module.setName(rs.getString("name"));
        module.setProductId(rs.getLong("product_id"));

       List<Option> options = optionDAO.findByModuleId(module.getId());
       module.setOptions(options);

       return module;
    }
}
