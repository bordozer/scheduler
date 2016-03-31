package scheduler.app.controllers.ui.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scheduler.app.utils.Parameters;

import java.security.Principal;

@Controller
@RequestMapping(Parameters.PORTAL_PAGE_URL)
public class PortalPageController {

	private static final String VIEW = "/PortalPage";

	@RequestMapping(method = RequestMethod.GET, value = "")
	public String portalPage(final Principal principal) {
		return VIEW;
	}
}
