package scheduler.web.controllers.rest.app;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import scheduler.core.services.SystemVarsService;
import scheduler.core.services.users.UserService;
import scheduler.web.converters.dto.UserDtoConverter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@RequestMapping("/rest/app")
public class AppRestController {

    @Inject
    private UserService userService;

    @Inject
    private SystemVarsService systemVarsService;

    @Inject
    private UserDtoConverter userDtoConverter;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public AppDTO applicationData(final ClientData clientData, final Principal principal, final HttpServletRequest request) {
        final String projectName = systemVarsService.getProjectName();
        final AppDTO dto = new AppDTO(projectName);
        if (principal != null) {
            dto.setCurrentUser(userDtoConverter.toDto(userService.findByLogin(principal.getName())));
        }
        return dto;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/authenticated/")
    public boolean authenticated(final Principal principal) {
        return principal != null;
    }
}
