package ru.easyjava.spring.data.jdbc.dao.mapping;

import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Rule;
import org.junit.Test;
import ru.easyjava.spring.data.jdbc.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.easymock.EasyMock.expect;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class OrderMapperTest extends EasyMockSupport {
    @Rule
    public EasyMockRule em = new EasyMockRule(this);

    @Mock
    private ResultSet rs;

    @TestSubject
    private OrderMapper testedObject = new OrderMapper();

    @Test
    public void testMapping() throws SQLException {
        expect(rs.getString("email")).andStubReturn("TEST");
        expect(rs.getInt("customer_id")).andStubReturn(100);
        expect(rs.getInt("id")).andStubReturn(500);

        replayAll();

        Order actual = testedObject.mapRow(rs, 0);

        assertThat(actual.getId(), is(500));
        assertThat(actual.getCustomer().getId(), is(100));
        assertThat(actual.getCustomer().getEmail(), is("TEST"));
    }

}