package scheduler.app.controllers.rest.users.edit;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/users")
public class UserEditRestController {

    @RequestMapping(method = RequestMethod.PUT, value = "/register/")
    public RegistrationResponse create(final @RequestBody UserEditDto editDTO) {
        return new RegistrationResponse(true);
    }
}
