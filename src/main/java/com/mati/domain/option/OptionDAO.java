package com.mati.domain.option;

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

   /* static private final String FIND_BY_ID = "select * from option o where o.id = :option_id";

    static private final String CREATE = "insert into option (item_id, module_id, price) " +
            "values (:itemid, :moduleId, :price)";

    static private final String DELETE = "delete from option o where o.id = :optionId";

    static private final String UPDATE = "update option set item_id = :itemId where id = :id";
*/
    NamedParameterJdbcTemplate jdbcTemplate;

    OptionRowMapper rowMapper;

    @Autowired
    public OptionDAO(NamedParameterJdbcTemplate jdbcTemplate, OptionRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
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

