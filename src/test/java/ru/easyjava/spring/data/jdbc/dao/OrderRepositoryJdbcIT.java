package ru.easyjava.spring.data.jdbc.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import ru.easyjava.spring.data.jdbc.entity.Customer;
import ru.easyjava.spring.data.jdbc.entity.Order;

import javax.inject.Inject;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@ContextConfiguration("/applicationContext.xml")
@SqlGroup({
        @Sql("/customers-table.sql"),
        @Sql("/customers-data.sql"),
        @Sql("/orders-table.sql"),
        @Sql("/orders-data.sql"),

})
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderRepositoryJdbcIT {
    @Inject
    private JdbcTemplate jdbcTemplate;

    @Inject
    private OrderRepositoryJdbc testedObject;

    @After
    public void tearDown() {
        JdbcTestUtils.dropTables(jdbcTemplate, "orders", "customers");
    }

    @Test
    public void testGet() {
        Order actual = testedObject.get(100);
        assertThat(actual.getId(), is(100));
        assertThat(actual.getCustomer().getId(), is(100));
        assertThat(actual.getCustomer().getEmail(), is("TEST"));
    }

    @Test
    public void testAll() {
        assertThat(testedObject.all().size(), is(7));
    }

    @Test
    public void testOrderCount() {
        Customer c = new Customer();
        c.setId(2);

        Number actual = testedObject.ordersForCustomer(c);

        int expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "orders", "customer_id=2");

        Assert.assertThat(actual, is(expected));
    }
}