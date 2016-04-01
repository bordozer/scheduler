package schemway.web.config.root;

import org.apache.log4j.Logger;
import org.hibernate.cache.ehcache.EhCacheRegionFactory;
import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import schemway.core.services.SystemVarsService;

import javax.inject.Inject;
import javax.persistence.SharedCacheMode;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Profile("development")
@EnableJpaRepositories("schemway.core.repositories")
@EnableTransactionManagement
public class DevelopmentConfiguration {

	private static final Logger LOGGER = Logger.getLogger(DevelopmentConfiguration.class);

	@Inject
	private SystemVarsService systemVarsService;

	@Bean(name = "datasource")
	public DriverManagerDataSource dataSource() {

		LOGGER.debug(String.format("Connection information: url=%s", systemVarsService.getDbUrl()));

		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl(String.format("%s", systemVarsService.getDbUrl()));
		dataSource.setUsername(systemVarsService.getDbUser());
		dataSource.setPassword(systemVarsService.getDbPassword());

		return dataSource;
	}

	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(final DriverManagerDataSource dataSource) {

		final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setPackagesToScan("schemway.core.entities");
		entityManagerFactoryBean.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

		final Map<String, Object> jpaProperties = new HashMap<String, Object>();
//		jpaProperties.put( "hibernate.hbm2ddl.auto", "create" ); // TODO: for DB upgrade only
		jpaProperties.put("hibernate.connection.CharSet", "utf8");
		jpaProperties.put("hibernate.connection.characterEncoding", "utf8");
		jpaProperties.put("hibernate.connection.useUnicode", "true");
		jpaProperties.put("hibernate.show_sql", "true");
		jpaProperties.put("hibernate.format_sql", "true");
		jpaProperties.put("hibernate.use_sql_comments", "true");
		jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		jpaProperties.put("hibernate.enable_lazy_load_no_trans", "true");

		jpaProperties.put("javax.persistence.sharedCache.mode", SharedCacheMode.ENABLE_SELECTIVE);
		jpaProperties.put(Environment.CACHE_REGION_FACTORY, EhCacheRegionFactory.class.getName());
		jpaProperties.put(Environment.USE_SECOND_LEVEL_CACHE, true);
		jpaProperties.put(Environment.USE_QUERY_CACHE, true);
		jpaProperties.put(Environment.GENERATE_STATISTICS, true);
		jpaProperties.put(Environment.USE_STRUCTURED_CACHE, true);

		entityManagerFactoryBean.setJpaPropertyMap(jpaProperties);

		return entityManagerFactoryBean;
	}

	@Bean
	public PersistentTokenRepository rememberMeTokenRepository() {

		final JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
		jdbcTokenRepository.setDataSource(dataSource());
//		jdbcTokenRepository.setCreateTableOnStartup( true ); // TODO: for DB upgrade only

		return jdbcTokenRepository;
	}
}
