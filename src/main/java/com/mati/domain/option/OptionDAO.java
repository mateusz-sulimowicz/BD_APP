package com.mati.domain.option;

import com.mati.domain.item.Item;
import com.mati.domain.item.ItemDAO;
import com.mati.domain.module.Module;
import org.apache.catalina.startup.ListenerCreateRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class OptionDAO {

    static private final String FIND_ALL_BY_MODULE_ID = "select * from option o where o.module_id = :moduleId";

    static private final String DELETE_BY_MODULE_ID = "delete from option where module_id = :moduleId";

    static private final String DELETE = "delete from option where module_id = :moduleId AND item_id = :itemId";

    static private final String CREATE = "insert into option (module_id, item_id, price) " +
            "values (:moduleId, :itemId, :price)";

    /* static private final String FIND_BY_ID = "select * from option o where o.id = :option_id";

     static private final String CREATE = "insert into option (item_id, module_id, price) " +
             "values (:itemid, :moduleId, :price)";

     static private final String DELETE = "delete from option o where o.id = :optionId";

     static private final String UPDATE = "update option set item_id = :itemId where id = :id";
 */
    NamedParameterJdbcTemplate jdbcTemplate;

    OptionRowMapper rowMapper;

    ItemDAO itemDAO;

    @Autowired
    public OptionDAO(NamedParameterJdbcTemplate jdbcTemplate, OptionRowMapper rowMapper, ItemDAO itemDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.itemDAO = itemDAO;
    }

    public List<Option> findByModuleId(Long id) {
        SqlParameterSource parameters = new MapSqlParameterSource("moduleId", id);

        return jdbcTemplate.query(FIND_ALL_BY_MODULE_ID, parameters, rowMapper);
    }

    public void deleteByModuleId(Long id) {
        SqlParameterSource parameters = new MapSqlParameterSource("moduleId", id);

        jdbcTemplate.update(DELETE_BY_MODULE_ID, parameters);
    }

    public void deleteByModuleAndItemIds(Long moduleId, Long itemId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("moduleId", moduleId)
                .addValue("itemId", itemId);

        jdbcTemplate.update(DELETE, parameters);
    }

    public Option create(Option option) {
        Item item = itemDAO
                .findByCode(option.getItem().getName())
                .orElseThrow(RuntimeException::new);

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("moduleId", option.getModuleId())
                .addValue("itemId", item.getId())
                .addValue("price", option.getPrice());

        jdbcTemplate.update(CREATE, parameters);

        return option;
    }

    public Option createCopyOf(Option option, Module module) {
        Option optionCopy = new Option();
        optionCopy.setModuleId(module.getId());
        optionCopy.setItem(option.getItem());
        optionCopy.setPrice(option.getPrice());

        return create(optionCopy);
    }


   /* public Item create(Item item) {
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
    }*/

}

