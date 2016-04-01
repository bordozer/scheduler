package schemway.web.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import schemway.scheduler.config.QuartzConfig;
import schemway.web.config.root.DevelopmentConfiguration;
import schemway.web.config.root.RootContextConfig;
import schemway.web.config.root.SecurityConfig;
import schemway.web.config.servlet.RequestListener;
import schemway.web.config.servlet.ServletContextConfig;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{
				RootContextConfig.class,
				DevelopmentConfiguration.class,
				SecurityConfig.class,
				QuartzConfig.class
		};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{ServletContextConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}

	@Override
	public void onStartup(final ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);

		servletContext.addListener(new SessionListener());
		servletContext.addListener(new RequestListener());
	}

	@Override
	protected Filter[] getServletFilters() {

		final CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);

		return new Filter[]{characterEncodingFilter};
	}
}
