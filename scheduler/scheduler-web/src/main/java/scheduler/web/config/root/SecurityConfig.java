package scheduler.web.config.root;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import scheduler.core.utils.Parameters;
import scheduler.web.security.AuthFailureHandler;
import scheduler.web.security.AuthSuccessHandler;
import scheduler.web.security.HttpAuthenticationEntryPoint;
import scheduler.web.security.HttpLogoutSuccessHandler;
import scheduler.web.security.SecurityUserDetailsService;

import javax.inject.Inject;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@ComponentScan(value = "scheduler.web.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Inject
	private SecurityUserDetailsService securityUserDetailsService;

	@Inject
	private HttpAuthenticationEntryPoint authenticationEntryPoint;

	@Inject
	private AuthSuccessHandler authSuccessHandler;

	@Inject
	private AuthFailureHandler authFailureHandler;

	@Inject
	private HttpLogoutSuccessHandler logoutSuccessHandler;

	@Inject
	private PersistentTokenRepository persistentTokenRepository;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	@Override
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return super.userDetailsServiceBean();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(securityUserDetailsService);
		authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());

		return authenticationProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/resources/**").permitAll()
				.antMatchers("/scheduler/**").permitAll()
				.antMatchers("/rest/translator/").permitAll()
				.antMatchers( "/rest/app/**" ).permitAll()
				.antMatchers( HttpMethod.PUT, "/rest/users/register/" ).permitAll()
//				.anyRequest().authenticated()
				.antMatchers( "/rest/" ).authenticated()
				.and()
				.authenticationProvider(authenticationProvider())
				.exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint)
				.and()
				.formLogin()
				.loginPage(Parameters.LOGIN_PAGE_URL)
				.permitAll()
				.loginProcessingUrl(Parameters.LOGIN_END_POINT)
				.usernameParameter(Parameters.USERNAME)
				.passwordParameter(Parameters.PASSWORD)
				.defaultSuccessUrl(Parameters.PORTAL_PAGE_URL)
				.successHandler(authSuccessHandler)
				.failureHandler(authFailureHandler)
				.and()
				.logout()
				.permitAll()
				.logoutUrl("/logout")
				.logoutSuccessHandler(logoutSuccessHandler)
				.logoutSuccessUrl(Parameters.LOGIN_PAGE_URL)
				.and()
				.rememberMe()
				.tokenRepository(persistentTokenRepository)
				.rememberMeServices(rememberMeServices())
				.key(Parameters.REMEMBER_ME_KEY)
				.and()
				.sessionManagement()
				.maximumSessions(1)
		;

		http.authorizeRequests().anyRequest().authenticated();
	}

	@Bean
	public TokenBasedRememberMeServices rememberMeServices() {

		final TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices(Parameters.REMEMBER_ME_KEY, securityUserDetailsService);
		rememberMeServices.setTokenValiditySeconds(1209600);
		rememberMeServices.setCookieName("Scheduler_Micro_Service_Remember_Me_Cookie");

		return rememberMeServices;
	}
}