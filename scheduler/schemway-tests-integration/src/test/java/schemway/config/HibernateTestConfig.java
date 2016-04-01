package schemway.config;

import org.hibernate.cache.ehcache.EhCacheRegionFactory;
import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.SharedCacheMode;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("schemway.core.repositories")
@ComponentScan({
        "schemway.core.repositories",
        "schemway.core.services",
        "schemway.core.converters"
})
public class HibernateTestConfig {

    public static final String MIGRATION_SCHEMA_SQL = "migration/V1__schema.sql";

    private static final String SCHEMWAY_ENTITIES = "schemway.core.entities";

    @Bean(name = "dataSource")
    public EmbeddedDatabase dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript(MIGRATION_SCHEMA_SQL)
                .build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(final DataSource dataSource) {

        final Map<String, Object> jpaProperties = new HashMap<>();
        jpaProperties.put(Environment.HBM2DDL_AUTO, "none"); // change to validate and fix errors later
        jpaProperties.put("hibernate.connection.CharSet", "utf8");
        jpaProperties.put("hibernate.connection.characterEncoding", "utf8");
        jpaProperties.put("hibernate.connection.useUnicode", "true");
        jpaProperties.put("hibernate.show_sql", true);
        jpaProperties.put("hibernate.format_sql", true);
        jpaProperties.put("hibernate.use_sql_comments", true);
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        jpaProperties.put("hibernate.enable_lazy_load_no_trans", true);
        jpaProperties.put("hibernate.current_session_context_class", "thread");

        jpaProperties.put("javax.persistence.sharedCache.mode", SharedCacheMode.NONE);
        jpaProperties.put(Environment.CACHE_REGION_FACTORY, EhCacheRegionFactory.class.getName());
        jpaProperties.put(Environment.USE_SECOND_LEVEL_CACHE, false);
        jpaProperties.put(Environment.USE_QUERY_CACHE, false);
        jpaProperties.put(Environment.GENERATE_STATISTICS, false);
        jpaProperties.put(Environment.USE_STRUCTURED_CACHE, false);

        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPackagesToScan(SCHEMWAY_ENTITIES);
        entityManagerFactory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setJpaPropertyMap(jpaProperties);

        return entityManagerFactory;
    }
}
