package scheduler.app.controllers.rest.users.edit;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import scheduler.app.dto.UserDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/rest/users")
public class UserEditRestController {

    @RequestMapping(method = RequestMethod.PUT, value = "/register/")
    public RegistrationResponse create(final @Valid @RequestBody UserEditDto editDTO) {
        UserDto user = new UserDto();
        user.setUserId(111L);
        user.setUserName(editDTO.getName());

        RegistrationResponse registrationResponse = new RegistrationResponse(true);
        registrationResponse.setUser(user);
        return registrationResponse;
    }
}
