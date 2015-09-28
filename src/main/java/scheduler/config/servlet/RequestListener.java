package scheduler.config.servlet;

import org.springframework.web.context.request.RequestContextListener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RequestListener extends RequestContextListener {

	@Override
	public void requestInitialized( final ServletRequestEvent requestEvent ) {
		super.requestInitialized( requestEvent );

		final HttpServletRequest request = ( HttpServletRequest ) requestEvent.getServletRequest();

		initContext( request );
	}

	private void initContext( final HttpServletRequest request ) {
		final HttpSession session = request.getSession();
	}
}
