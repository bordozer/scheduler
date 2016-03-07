package scheduler.app.services;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Service
public class SystemVarsServiceImpl implements SystemVarsService {

	private static final Logger LOGGER = Logger.getLogger(SystemVarsServiceImpl.class);

	private final CompositeConfiguration config = new CompositeConfiguration();

	public void init() throws ConfigurationException, IOException {

		final List<String> propertyFiles = newArrayList();
		propertyFiles.add("database");
		propertyFiles.add("system");

		config.clear();

		for (final String propertyFileName : propertyFiles) {
			final File propertyFile = getPropertyFile(propertyFileName);

			LOGGER.debug(String.format("Loading configuration from file '%s'", propertyFile.getCanonicalPath()));

			if (!propertyFile.exists()) {
				final String message = String.format("Property file '%s' does not exist!", propertyFile.getCanonicalPath());
				LOGGER.error(message);
				throw new IOException(message);
			}

			config.addConfiguration(new PropertiesConfiguration(propertyFile));
		}

		LOGGER.debug("Configurations have been loaded");
	}

	@Override
	public String getProjectName() {
		return config.getString("system.projectName");
	}

	@Override
	public String getDbUrl() {
		return config.getString("db.default.url");
	}

	@Override
	public String getDbUser() {
		return config.getString("db.default.user");
	}

	@Override
	public String getDbPassword() {
		return config.getString("db.default.password");
	}

	@Override
	public boolean showDbLog() {
		return config.getBoolean("db.default.logStatements", false);
	}

	private File getPropertyFile(final String fileName) {
		return new File(String.format("%s/%s.properties", getPropertiesPath(), fileName));
	}

	private String getPropertiesPath() {
		return "src/main/resources";
	}
}
