package ru.easyjava.spring.data.jdbc.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

/**
 * JDBC implementation of CustomerRepository.
 */
@Repository
public class CustomerRepositoryJdbc implements CustomerRepository {
    /**
     * Email retrieval query.
     */
    private static final String EMAIL_QUERY
            = "SELECT EMAIL FROM CUSTOMERS WHERE ID=?";

    /**
     * Non-parameterized email retrieval query.
     */
    private static final String ALL_EMAIL_QUERY
            = "SELECT EMAIL FROM CUSTOMERS";

    /**
     * All customers retrieval query.
     */
    private static final String ALL_QUERY
            = "SELECT * FROM CUSTOMERS ORDER BY ID DESC";

    /**
     * Customer creation query.
     */
    private static final String CREATE_QUERY
            = "INSERT INTO CUSTOMERS (EMAIL) VALUES(?) RETURNING ID";

    /**
     * Database connection.
     */
    @Inject
    private JdbcTemplate jdbcTemplate;

    @Override
    public final Number add(final String email) {
        return jdbcTemplate.queryForObject(CREATE_QUERY, Long.class, email);
    }

    @Override
    public final String getEmail(final Long id) {
        return jdbcTemplate.queryForObject(EMAIL_QUERY, String.class, id);
    }

    @Override
    public final List<String> allEmails() {
        return jdbcTemplate.queryForList(ALL_EMAIL_QUERY, String.class);
    }

    @Override
    public final List<Map<String, Object>> all() {
        return jdbcTemplate.queryForList(ALL_QUERY);
    }
}
