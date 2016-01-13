package ru.easyjava.spring.data.jdbc.dao;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.easyjava.spring.data.jdbc.entity.Sku;

import javax.inject.Inject;

/**
 * JDBC based implementation of the SKU repository.
 */
@Repository
public class SkuRepositoryJdbc implements SkuRepository {
    /**
     * Query for description retrieval.
     */
    private static final String DESCRIPTION_QUERY
            = "SELECT DESCRIPTION FROM SKUS WHERE ID = :id";

    /**
     * Query for SKU creation.
     */
    private static final String ADD_QUERY
            = "INSERT INTO SKUS(ID, DESCRIPTION) VALUES(:id, :description)";

    /**
     * Database object.
     */
    @Inject
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public final void add(final Sku sku) {
        jdbcTemplate.update(ADD_QUERY, new BeanPropertySqlParameterSource(sku));
    }

    @Override
    public final String getDescription(final Integer id) {
        SqlParameterSource namedParameters
                = new MapSqlParameterSource("id", id);
        //Map<String,Object> namedParameters
        //      = Collections.singletonMap("id", id);
        return jdbcTemplate.queryForObject(
                DESCRIPTION_QUERY,
                namedParameters,
                String.class);
    }

    @Override
    public final String getThirdSkuDescription() {
        return jdbcTemplate.queryForObject(
                DESCRIPTION_QUERY,
                new AlwaysThreeParametersSource(),
                String.class);
    }
}
