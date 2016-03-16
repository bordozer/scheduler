package scheduler.app.repositories;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.dbunit.DatabaseUnitException;
import org.dbunit.ext.h2.H2Connection;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import scheduler.config.HibernateTestConfig;

import java.sql.SQLException;

public abstract class AbstractRepositoryTest {

    protected final static TestUser USER_CURRY = new TestUser(1L, "Steph Curry");
    protected final static TestUser USER_IBAKA = new TestUser(2L, "Serge Ibaka");
    protected final static TestUser USER_DURANT = new TestUser(3L, "Kevin Durant");

    @Configuration
    @EnableJpaRepositories("scheduler.app.repositories")
    @ComponentScan({
            "scheduler.app.repositories",
            "scheduler.app.services",
            "scheduler.app.converters"
    })
    public static class Config {
        @Bean
        public org.dbunit.ext.h2.H2Connection h2TestConnection(final DriverManagerDataSource dataSource) throws DatabaseUnitException, SQLException {
            return new H2Connection(dataSource.getConnection(), HibernateTestConfig.TEST_SCHEMA_NAME);
        }
    }

    @BeforeClass
    public static void prepareFakeApplication() {
    }

    @AfterClass
    public static void finish() {
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class TestUser {
        private Long id;
        private String userName;
    }
}
