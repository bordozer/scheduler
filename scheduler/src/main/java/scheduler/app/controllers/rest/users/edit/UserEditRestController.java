package scheduler.app.controllers.rest.users.edit;

import com.beust.jcommander.internal.Maps;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import scheduler.app.dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

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
    @ExceptionHandler(value = Exception.class)
    public @ResponseBody Map<String, Object> handleException(final Exception e, final Writer writer) throws IOException {
        writer.write(new Gson().toJson(e));
        return Maps.newHashMap();
    }
}
