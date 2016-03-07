package scheduler.app.controllers.rest.users.edit;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import scheduler.app.esceptions.BackendException;
import scheduler.app.dto.UserDto;
import scheduler.app.esceptions.FieldErrorResource;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.stream.Collectors;

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
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public void handleException(final Exception e, final Writer writer) throws IOException {
        List<FieldError> fieldErrors = ((MethodArgumentNotValidException) e).getBindingResult().getFieldErrors();
        List<FieldErrorResource> response = fieldErrors.stream()
                .map(fieldError -> new FieldErrorResource(
                        fieldError.getField(),
                        fieldError.getRejectedValue().toString(),
                        fieldError.getDefaultMessage()))
                .collect(Collectors.toList()
                );
        writer.write(new Gson().toJson(response));
//        writer.write(new Gson().toJson(((MethodArgumentNotValidException) e).getBindingResult().getAllErrors()));
    }
}
