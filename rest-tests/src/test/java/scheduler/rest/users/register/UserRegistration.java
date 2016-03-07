package scheduler.rest.users.register;

import com.jayway.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import scheduler.rest.common.ResourcePath;
import scheduler.rest.common.RestTestHelper;
import scheduler.rest.common.UserData;
import scheduler.rest.common.UserRoutes;
import scheduler.rest.dto.RegistrationResponse;
import scheduler.rest.dto.UserDto;
import scheduler.rest.errors.FieldErrorResource;
import scheduler.rest.errors.ResponseExceptionsHolder;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class UserRegistration {

    @Test
    public void shouldNotRegisterUserIfNoDataProvided() {
        String requestBody = RestTestHelper.readJson(ResourcePath.USER_REGISTRATION_EMPTY_DATA_JSON);

        Response response = RestTestHelper.doJsonPut(requestBody, UserRoutes.USER_REGISTRATION, HttpStatus.SC_BAD_REQUEST);

        ResponseExceptionsHolder registrationResponse = response.as(ResponseExceptionsHolder.class);
        List<FieldErrorResource> loginErrors = registrationResponse.getFieldError("login");
        List<FieldErrorResource> nameErrors = registrationResponse.getFieldError("name");
        List<FieldErrorResource> passwordErrors = registrationResponse.getFieldError("password");
        List<FieldErrorResource> passwordConfirmErrors = registrationResponse.getFieldError("passwordConfirm");

        assertEquals(8, registrationResponse.errorsCount());

        assertEquals("login", loginErrors.get(0).getField());
        assertEquals("errors.user_login_must_not_be_empty", loginErrors.get(0).getErrorCode());
        assertTrue(StringUtils.isEmpty(loginErrors.get(0).getRejectedValue()));

        assertEquals("name", nameErrors.get(0).getField());
        assertEquals("errors.user_name_must_not_be_empty", nameErrors.get(0).getErrorCode());
        assertTrue(StringUtils.isEmpty(nameErrors.get(0).getRejectedValue()));

        assertEquals("password", passwordErrors.get(0).getField());
        assertEquals("errors.user_password_must_not_be_empty", passwordErrors.get(0).getErrorCode());
        assertTrue(StringUtils.isEmpty(passwordErrors.get(0).getRejectedValue()));

        assertEquals("passwordConfirm", passwordConfirmErrors.get(0).getField());
        assertEquals("errors.user_password_confirm_must_not_be_empty", passwordConfirmErrors.get(0).getErrorCode());
        assertTrue(StringUtils.isEmpty(passwordConfirmErrors.get(0).getRejectedValue()));
    }

    @Test
    public void shouldRegisterUser() {
        UserData userData = new UserData("login", "user_name", "password");
        String requestBody = String.format(RestTestHelper.readJson(ResourcePath.USER_REGISTRATION_DATA_JSON),
                userData.getLogin(),
                userData.getName(),
                userData.getPassword(),
                userData.getPassword()
        );

        Response response = RestTestHelper.doJsonPut(requestBody, UserRoutes.USER_REGISTRATION, HttpStatus.SC_OK);

        RegistrationResponse registrationResponse = response.as(RegistrationResponse.class);
        assertTrue(registrationResponse.isSuccess());
        assertTrue(registrationResponse.getErrors().size() == 0);

        UserDto user = registrationResponse.getUser();
        assertTrue(user.getUserId() != null && user.getUserId() > 0);
        assertEquals(userData.getName(), user.getUserName());
    }
}
