package scheduler.web.controllers.rest.users.edit;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import scheduler.web.controllers.rest.users.edit.converters.UserEditDtoConverter;
import scheduler.web.controllers.rest.users.edit.dto.NewUserDto;
import scheduler.web.controllers.rest.users.edit.dto.RegistrationResponse;
import scheduler.web.converters.UserDtoConverter;
import scheduler.web.dto.UserDto;

import javax.inject.Inject;

@RestController
@RequestMapping("/rest/users")
public class UserEditRestController {

    @Inject
    private UserEditDtoConverter userEditDtoConverter;

    @Inject
    private UserDtoConverter userDtoConverter;

    @RequestMapping(method = RequestMethod.PUT, value = "/register/")
    public RegistrationResponse create(final @Validated @RequestBody NewUserDto editDTO) {
        UserDto userDto = userDtoConverter.toDto(userEditDtoConverter.createUser(editDTO));
        RegistrationResponse registrationResponse = new RegistrationResponse(true);
        registrationResponse.setUser(userDto);
        return registrationResponse;
    }
}
