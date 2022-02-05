package com.mimuw.domain.module;

import com.mimuw.domain.option.Option;
import com.mimuw.domain.option.OptionDAO;
import com.mimuw.domain.option.OptionRowMapper;
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
public class ModuleDAO {

    static private final String FIND_ALL = "select * from module m";

    static private final String FIND_BY_ID = "select * from module m where m.id = :moduleId";

    static private final String FIND_ALL_BY_PRODUCT = "select m.* from module m where m.product_id = :productId";

    static private final String CREATE = "insert into module (name, product_id) values (:name, :productId)";

    static private final String DELETE = "delete from module where id = :moduleId";

    /*static private final String CREATE = "insert into option (item_id, module_id, price) " +
            "values (:itemid, :moduleId, :price)";

    static private final String DELETE = "delete from option o where o.id = :optionId";

    static private final String UPDATE = "update option set item_id = :itemId where id = :id";*/

    NamedParameterJdbcTemplate jdbcTemplate;

    OptionRowMapper rowMapper;

    ModuleRowMapper moduleRowMapper;

    OptionDAO optionDAO;

    public ModuleDAO(NamedParameterJdbcTemplate jdbcTemplate,
                     OptionRowMapper rowMapper,
                     ModuleRowMapper moduleRowMapper,
                     OptionDAO optionDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.moduleRowMapper = moduleRowMapper;
        this.optionDAO = optionDAO;
    }

    public List<Module> findAll() {
        return jdbcTemplate.query(FIND_ALL, moduleRowMapper);
    }

    public List<Module> findAllByProductId(Long id) {
        SqlParameterSource parameters = new MapSqlParameterSource("productId", id);

        return jdbcTemplate.query(FIND_ALL_BY_PRODUCT, parameters, moduleRowMapper);
    }

    public Optional<Module> findById(Long id) {
        SqlParameterSource parameters = new MapSqlParameterSource("moduleId", id);

        try {
            Module foundModule = jdbcTemplate.queryForObject(FIND_BY_ID, parameters, moduleRowMapper);
            return Optional.of(foundModule);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void deleteById(Long id) {
        optionDAO.deleteByModuleId(id);

        SqlParameterSource parameters = new MapSqlParameterSource("moduleId", id);

        jdbcTemplate.update(DELETE, parameters);
    }

    public Module create(Module module) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", module.getName())
                .addValue("productId", module.getProductId());

        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(CREATE, parameters, holder);

        Integer key = (Integer) holder.getKeys().get("id");
        module.setId(key.longValue());

        return module;
    }

    public Module createCopyOf(Module module, Long productId) {
        var moduleCopy = new Module();
        moduleCopy.setProductId(productId);
        moduleCopy.setName(module.getName());
        moduleCopy = create(moduleCopy);

        List<Option> moduleCopyOptions = new ArrayList<>();
        for (var option : module.getOptions()) {
            moduleCopyOptions.add(optionDAO.createCopyOf(option, moduleCopy));
        }

        moduleCopy.setOptions(moduleCopyOptions);
        return moduleCopy;
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

