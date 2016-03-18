package scheduler.config;

import org.hibernate.cache.ehcache.EhCacheRegionFactory;
import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.SharedCacheMode;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
public class HibernateTestConfig {

    public static final String DB_NAME = "test";
    public static final String TEST_SCHEMA_NAME = "PUBLIC";
    public static final String DB_USERNAME = "sa";
    public static final String DB_USER_PASSWORD = "";

    private static final String MIGRATION_SCHEMA_SQL = "migration/V1__schema.sql";

    @Bean(name = "datasource")
    public DriverManagerDataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(org.h2.Driver.class.getName());
        dataSource.setUrl(String.format("jdbc:h2:mem:%s;MODE=Oracle;", DB_NAME)); //DB_CLOSE_DELAY
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_USER_PASSWORD);

        return dataSource;
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer() {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource(MIGRATION_SCHEMA_SQL));

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource());
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }

    @Bean
    public PlatformTransactionManager transactionManager(final DriverManagerDataSource dataSource) {
        return new JpaTransactionManager(entityManagerFactory(dataSource));
    }

    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory entityManagerFactory(final DriverManagerDataSource dataSource) {

        final Map<String, Object> jpaProperties = new HashMap<>();
        jpaProperties.put(Environment.HBM2DDL_AUTO, "create-drop");
        jpaProperties.put("hibernate.connection.CharSet", "utf8");
        jpaProperties.put("hibernate.connection.characterEncoding", "utf8");
        jpaProperties.put("hibernate.connection.useUnicode", "true");
        jpaProperties.put("hibernate.show_sql", true);
        jpaProperties.put("hibernate.format_sql", true);
        jpaProperties.put("hibernate.use_sql_comments", true);
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        jpaProperties.put("hibernate.enable_lazy_load_no_trans", true);

        jpaProperties.put("javax.persistence.sharedCache.mode", SharedCacheMode.NONE);
        jpaProperties.put(Environment.CACHE_REGION_FACTORY, EhCacheRegionFactory.class.getName());
        jpaProperties.put(Environment.USE_SECOND_LEVEL_CACHE, false);
        jpaProperties.put(Environment.USE_QUERY_CACHE, false);
        jpaProperties.put(Environment.GENERATE_STATISTICS, false);
        jpaProperties.put(Environment.USE_STRUCTURED_CACHE, false);

        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPackagesToScan("scheduler.app.entities");
        entityManagerFactory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setJpaPropertyMap(jpaProperties);
        entityManagerFactory.afterPropertiesSet();

        return entityManagerFactory.getObject();
    }
}
