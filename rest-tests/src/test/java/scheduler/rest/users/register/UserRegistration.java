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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class UserRegistration {

    @Test
    public void shouldNotRegisterUserIfNoDataProvided() {
        String requestBody = RestTestHelper.readJson(ResourcePath.USER_REGISTRATION_EMPTY_DATA_JSON);

        Response response = RestTestHelper.doJsonPut(requestBody, UserRoutes.USER_REGISTRATION, HttpStatus.SC_BAD_REQUEST);

        ResponseExceptionsHolder registrationResponse = response.as(ResponseExceptionsHolder.class);
        FieldErrorResource loginError = registrationResponse.getFieldError("login");
        FieldErrorResource nameError = registrationResponse.getFieldError("name");
        FieldErrorResource passwordError = registrationResponse.getFieldError("password");
        FieldErrorResource passwordConfirmError = registrationResponse.getFieldError("passwordConfirm");

        assertEquals(8, registrationResponse.errorsCount());

        assertEquals("login", loginError.getField());
        assertEquals("errors.user_login_must_not_be_empty", loginError.getErrorCode());
        assertTrue(StringUtils.isEmpty(loginError.getRejectedValue()));

        assertEquals("name", nameError.getField());
        assertEquals("errors.user_name_must_not_be_empty", nameError.getErrorCode());
        assertTrue(StringUtils.isEmpty(nameError.getRejectedValue()));

        assertEquals("password", passwordError.getField());
        assertEquals("errors.user_password_must_not_be_empty", passwordError.getErrorCode());
        assertTrue(StringUtils.isEmpty(passwordError.getRejectedValue()));

        assertEquals("passwordConfirm", passwordConfirmError.getField());
        assertEquals("errors.user_password_confirm_must_not_be_empty", passwordConfirmError.getErrorCode());
        assertTrue(StringUtils.isEmpty(passwordConfirmError.getRejectedValue()));
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
