package ru.easyjava.spring.data.jdbc.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import ru.easyjava.spring.data.jdbc.entity.Sku;

import javax.inject.Inject;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@ContextConfiguration("/applicationContext.xml")
@SqlGroup({
        @Sql("/skus-table.sql"),
        @Sql("/skus-data.sql")

})
@Sql( scripts = "/skus-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
public class SkuRepositoryJdbcIT {
    @Inject
    private JdbcTemplate jdbcTemplate;

    @Inject
    private SkuRepositoryJdbc testedObject;

    @Test
    public void testGetDescription() {
        assertThat(testedObject.getDescription(100), is("TEST"));
    }

    @Test
    public void testGetThirdDescription() {
        assertThat(testedObject.getThirdSkuDescription(), is("Sample SKU #3"));
    }

    @Test
    public void testCreate() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "skus");

        Sku expected = new Sku();
        expected.setId(1);
        expected.setDescription("NEWBIE");

        testedObject.add(expected);

        assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "skus"), is(1));
    }
}