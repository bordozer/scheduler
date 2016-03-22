package scheduler.app;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.jdbc.JdbcTestUtils;
import scheduler.config.HibernateTestConfig;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateTestConfig.class})
@DbUnitConfiguration(databaseConnection = "dataSource")
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class
})
public abstract class AbstractIntegrationTest {

    protected final static TestUser USER_CURRY = new TestUser(1L, "Steph Curry");
    protected final static TestUser USER_IBAKA = new TestUser(2L, "Serge Ibaka");
    protected final static TestUser USER_DURANT = new TestUser(3L, "Kevin Durant");

    @Inject
    private JdbcTemplate jdbcTemplate;

    @After
    public void cleanDatabase() throws Exception {
//        JdbcTestUtils.deleteFromTables(jdbcTemplate, "T_USER_SECURITY", "T_USER");
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class TestUser {
        private Long id;
        private String userName;
    }
}
