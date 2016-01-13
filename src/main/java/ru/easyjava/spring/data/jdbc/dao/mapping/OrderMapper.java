package ru.easyjava.spring.data.jdbc.dao.mapping;

import org.springframework.jdbc.core.RowMapper;
import ru.easyjava.spring.data.jdbc.entity.Customer;
import ru.easyjava.spring.data.jdbc.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Example of RowMapper.
 */
public class OrderMapper implements RowMapper<Order> {
    @Override
    public final Order mapRow(final ResultSet resultSet, final int i)
            throws SQLException {
        Customer customer = new Customer();
        customer.setId(resultSet.getInt("customer_id"));
        customer.setEmail(resultSet.getString("email"));

        Order order = new Order();
        order.setId(resultSet.getInt("id"));
        order.setCustomer(customer);

        return order;
    }
}
