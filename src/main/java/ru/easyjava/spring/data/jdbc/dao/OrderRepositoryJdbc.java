package ru.easyjava.spring.data.jdbc.dao;

import lombok.Getter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import ru.easyjava.spring.data.jdbc.dao.mapping.OrderMapper;
import ru.easyjava.spring.data.jdbc.entity.Customer;
import ru.easyjava.spring.data.jdbc.entity.Order;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * JDBC based implementation of OrderRepository.
 */
@Repository
public class OrderRepositoryJdbc implements OrderRepository {
    /**
     * Query for specific order.
     */
    private static final String ORDER_QUERY
            = "SELECT O.ID, CUSTOMER_ID, EMAIL "
            + "FROM ORDERS AS O, CUSTOMERS AS C "
            + "WHERE C.ID=O.CUSTOMER_ID AND O.ID=?";

    /**
     * Non parameterized order query.
     */
    private static final String ALL_QUERY
            = "SELECT O.ID, CUSTOMER_ID, EMAIL "
            + "FROM ORDERS AS O, CUSTOMERS AS C "
            + "WHERE C.ID=O.CUSTOMER_ID";

    /**
     * Database connection.
     */
    @Inject
    private JdbcTemplate jdbcTemplate;

    @Override
    public final Order get(final Integer id) {
        return jdbcTemplate.queryForObject(ORDER_QUERY, new OrderMapper(), id);
    }

    @Override
    public final List<Order> all() {
        return jdbcTemplate.query(ALL_QUERY, new OrderMapper());
    }

    /**
     * PreparedStatementCreator example.
     */
    private static final class OrderCountCreator
            implements PreparedStatementCreator {
        /**
         * Order counting query.
         */
        private static final String ORDERS_COUNT
                = "SELECT COUNT(ID) FROM ORDERS WHERE CUSTOMER_ID=?";

        /**
         * We keep customer here and use it in a callback.
         */
        private Customer customer;

        /**
         * Well, constructor.
         * @param c customer object to work on.
         */
        public OrderCountCreator(final Customer c) {
            this.customer = c;
        }

        @Override
        public PreparedStatement createPreparedStatement(final Connection con)
                throws SQLException {
            PreparedStatement ps = con.prepareStatement(ORDERS_COUNT);
            ps.setInt(1, customer.getId());
            return ps;
        }
    }

    /**
     * RowCallbackHandler example.
     */
    private static final class OrderCountHandler implements RowCallbackHandler {

        /**
         * Here we accumulate results.
         */
        @Getter
        private Integer result = 0;

        @Override
        public void processRow(final ResultSet rs) throws SQLException {
            result += rs.getInt(1);
        }
    }

    @Override
    public final Number ordersForCustomer(final Customer c) {
        OrderCountHandler handler = new OrderCountHandler();
        jdbcTemplate.query(new OrderCountCreator(c), handler);
        return handler.getResult();
    }
}
