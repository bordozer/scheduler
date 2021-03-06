package scheduler.rest.users;

import com.jayway.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.hamcrest.Matcher;
import org.testng.annotations.Test;
import scheduler.rest.common.DataGenerator;
import scheduler.rest.common.ResourcePath;
import scheduler.rest.common.RestTestHelper;
import scheduler.rest.common.UserData;
import scheduler.rest.common.routes.UserRoutes;
import scheduler.rest.dto.RegistrationResponse;
import scheduler.rest.dto.UserDto;
import scheduler.rest.errors.ResponseExceptionsHolder;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class UserRegistration {

    public static final String FIELD_LOGIN = "login";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_PASSWORD = "password";
    public static final String FIELD_PASSWORD_CONFIRM = "passwordConfirm";
    public static final Matcher<Object> NOT_NULL_VALUE = notNullValue();

    @Test
    public void shouldNotRegisterUserIfNoDataProvided() {
        String requestBody = RestTestHelper.readJson(ResourcePath.USER_REGISTRATION_EMPTY_DATA_JSON);

        Response response = RestTestHelper.doJsonPut(requestBody, UserRoutes.USER_REGISTRATION, HttpStatus.SC_BAD_REQUEST);

        ResponseExceptionsHolder registrationResponse = response.as(ResponseExceptionsHolder.class);

        assertThat(8, is(registrationResponse.errorsCount()));

        assertThat(registrationResponse.getFieldErrorResource(FIELD_LOGIN, "errors.user_login_must_not_be_empty"), NOT_NULL_VALUE);
        assertThat(registrationResponse.getFieldErrorResource(FIELD_LOGIN, "errors.login_too_long"), NOT_NULL_VALUE);

        assertThat(registrationResponse.getFieldErrorResource(FIELD_NAME, "errors.user_name_must_not_be_empty"), NOT_NULL_VALUE);
        assertThat(registrationResponse.getFieldErrorResource(FIELD_NAME, "errors.name_too_long"), NOT_NULL_VALUE);

        assertThat(registrationResponse.getFieldErrorResource(FIELD_PASSWORD, "errors.user_password_must_not_be_empty"), NOT_NULL_VALUE);
        assertThat(registrationResponse.getFieldErrorResource(FIELD_PASSWORD, "errors.password_too_long"), NOT_NULL_VALUE);

        assertThat(registrationResponse.getFieldErrorResource(FIELD_PASSWORD_CONFIRM, "errors.user_password_confirm_must_not_be_empty"), NOT_NULL_VALUE);
        assertThat(registrationResponse.getFieldErrorResource(FIELD_PASSWORD_CONFIRM, "errors.password_confirmation_too_long"), NOT_NULL_VALUE);
    }

    @Test
    public void shouldRegisterUser() {
        UserData userData = DataGenerator.generateUserData();
        Response response = RestTestHelper.register(userData.getLogin(), userData.getPassword(), userData.getName());

        RegistrationResponse registrationResponse = response.as(RegistrationResponse.class);
        assertTrue(registrationResponse.isSuccess());
        assertTrue(registrationResponse.getErrors().size() == 0);

        UserDto user = registrationResponse.getUser();
        assertTrue(user.getUserId() != null && user.getUserId() > 0);
        assertEquals(userData.getName(), user.getUserName());
    }
}
