package com.mati.domain.option;

import com.mati.domain.item.Item;
import com.mati.domain.item.ItemDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Service
public class OptionRowMapper implements RowMapper<Option> {

    ItemDAO itemDAO;

    @Autowired
    public OptionRowMapper(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    @Override
    public Option mapRow(ResultSet rs, int rowNum) throws SQLException {
        Option option = new Option();
        Item item = itemDAO
                .findById(rs.getLong("item_id"))
                .orElseThrow(RuntimeException::new);;

        option.setPrice(rs.getBigDecimal("price"));
        option.setModuleId(rs.getLong("module_id"));
        option.setItem(item);

        return option;
    }
}
