package scheduler.rest.users.register;

import com.jayway.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import scheduler.rest.common.ResourcePath;
import scheduler.rest.common.RestTestHelper;
import scheduler.rest.common.UserData;
import scheduler.rest.common.UserRoutes;
import scheduler.rest.dto.RegistrationResponse;
import scheduler.rest.dto.UserDto;
import scheduler.rest.errors.ResponseExceptionsHolder;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class UserRegistration {

    public static final String FIELD_LOGIN = "login";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_PASSWORD = "password";
    public static final String FIELD_PASSWORD_CONFIRM = "passwordConfirm";

    @Test
    public void shouldNotRegisterUserIfNoDataProvided() {
        String requestBody = RestTestHelper.readJson(ResourcePath.USER_REGISTRATION_EMPTY_DATA_JSON);

        Response response = RestTestHelper.doJsonPut(requestBody, UserRoutes.USER_REGISTRATION, HttpStatus.SC_BAD_REQUEST);

        ResponseExceptionsHolder registrationResponse = response.as(ResponseExceptionsHolder.class);

        assertNotNull(registrationResponse.getFieldErrorResource(FIELD_LOGIN, "errors.user_login_must_not_be_empty"));
        assertNotNull(registrationResponse.getFieldErrorResource(FIELD_LOGIN, "errors.login_too_long"));

        assertNotNull(registrationResponse.getFieldErrorResource(FIELD_NAME, "errors.user_name_must_not_be_empty"));
        assertNotNull(registrationResponse.getFieldErrorResource(FIELD_NAME, "errors.name_too_long"));

        assertNotNull(registrationResponse.getFieldErrorResource(FIELD_PASSWORD, "errors.user_password_must_not_be_empty"));
        assertNotNull(registrationResponse.getFieldErrorResource(FIELD_PASSWORD, "errors.password_too_long"));

        assertNotNull(registrationResponse.getFieldErrorResource(FIELD_PASSWORD_CONFIRM, "errors.user_password_confirm_must_not_be_empty"));
        assertNotNull(registrationResponse.getFieldErrorResource(FIELD_PASSWORD_CONFIRM, "errors.password_confirmation_too_long"));
    }

    @Test
    public void shouldRegisterUser() {
        UserData userData = new UserData(FIELD_LOGIN, "user_name", FIELD_PASSWORD);
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
