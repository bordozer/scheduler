package scheduler.config.root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import scheduler.app.security.AjaxAuthenticationSuccessHandler;
import scheduler.app.security.SecurityUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String LOGIN_PAGE_URL = "/resources/public/login/login.html";
    //	public static final String REMEMBER_ME_KEY = "Scheduler_Micro_Service_myAppKey";
    public static final String REMEMBER_ME_KEY = "myAppKey";

    public static final String PORTAL_PAGE_URL = "/scheduler/";

    @Autowired
    private SecurityUserDetailsService userDetailsService;

    @Autowired
    private AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;

    @Autowired
    private PersistentTokenRepository persistentTokenRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .eraseCredentials(true)
                .userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/resources/public/**").permitAll()
                .antMatchers("/resources/images*//**").permitAll()
                .antMatchers("/resources/bower_components*//**").permitAll()
                .antMatchers("/rest/translator/").permitAll()
//					.antMatchers( "/rest/app/" ).permitAll()
//					.antMatchers( HttpMethod.PUT, "/rest/users/create/" ).permitAll()
//					.antMatchers( "/admin/**" ).hasRole( "ADMIN" )
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl(PORTAL_PAGE_URL)
                .loginProcessingUrl("/authenticate")
                .usernameParameter("login")
                .passwordParameter("password")
                .successHandler(ajaxAuthenticationSuccessHandler)
                .failureUrl("/login?error") // TODO: implement beautiful error page
                .loginPage(LOGIN_PAGE_URL)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl(LOGIN_PAGE_URL)
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository)
                .rememberMeServices(rememberMeServices())
                .key(REMEMBER_ME_KEY);
    }

    @Bean
    public TokenBasedRememberMeServices rememberMeServices() {

        final TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices(REMEMBER_ME_KEY, userDetailsService);
        rememberMeServices.setTokenValiditySeconds(1209600);
        rememberMeServices.setCookieName("Scheduler_Micro_Service_Remember_Me_Cookie");

        return rememberMeServices;
    }
}
