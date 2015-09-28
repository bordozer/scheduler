package scheduler.app.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import scheduler.config.root.SecurityConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private AuthenticationSuccessHandler defaultHandler;

	public AjaxAuthenticationSuccessHandler() {
		final SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareSuccessHandler = new SavedRequestAwareAuthenticationSuccessHandler();
		savedRequestAwareSuccessHandler.setTargetUrlParameter( SecurityConfig.PORTAL_PAGE_URL );
		this.defaultHandler = savedRequestAwareSuccessHandler;
	}

	@Override
	public void onAuthenticationSuccess( final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication ) throws IOException, ServletException {

		if ( "true".equals( request.getHeader( "X-Login-Ajax-call" ) ) ) {
			response.getWriter().print( "ok" );
			response.getWriter().flush();
		} else {
			defaultHandler.onAuthenticationSuccess( request, response, authentication );
		}
	}
}
