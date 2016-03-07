package scheduler.app.controllers.rest.users.edit;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import scheduler.app.dto.UserDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/rest/users")
public class UserEditRestController {

    @RequestMapping(method = RequestMethod.PUT, value = "/register/")
    public RegistrationResponse create(final @Validated @RequestBody UserEditDto editDTO) {
        UserDto user = new UserDto();
        user.setUserId(111L);
        user.setUserName(editDTO.getName());

        RegistrationResponse registrationResponse = new RegistrationResponse(true);
        registrationResponse.setUser(user);
        return registrationResponse;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler//(value = IllegalArgumentException.class)
    public void conflict() {
        // Nothing to do
    }
}
