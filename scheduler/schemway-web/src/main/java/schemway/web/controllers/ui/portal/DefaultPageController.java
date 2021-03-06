package schemway.web.controllers.ui.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import schemway.web.utils.Parameters;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("")
public class DefaultPageController {

	@RequestMapping(method = RequestMethod.GET)
	public void redirectToPortalPage(HttpServletResponse response) throws IOException {
		response.sendRedirect(Parameters.PORTAL_PAGE_URL);
	}
}