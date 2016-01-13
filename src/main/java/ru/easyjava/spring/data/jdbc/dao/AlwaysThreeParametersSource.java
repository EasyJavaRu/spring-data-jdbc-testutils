package ru.easyjava.spring.data.jdbc.dao;

import org.springframework.jdbc.core.namedparam.AbstractSqlParameterSource;

import java.sql.Types;

/**
 * Example of custom SqlParameterSource.
 */
public class AlwaysThreeParametersSource extends AbstractSqlParameterSource {
    /**
     * We always response with that value.
     */
    private static final Integer FIXED_ID = 3;
    @Override
    public final boolean hasValue(final String s) {
        return true;
    }

    @Override
    public final Object getValue(final String s)
            throws IllegalArgumentException {
        return FIXED_ID;
    }

    @Override
    public final int getSqlType(final String s) {
        return Types.INTEGER;
    }
}
