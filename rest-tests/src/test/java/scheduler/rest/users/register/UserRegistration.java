package scheduler.rest.users.register;

import com.jayway.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import scheduler.rest.common.DataGenerator;
import scheduler.rest.common.ResourcePath;
import scheduler.rest.common.RestTestHelper;
import scheduler.rest.common.UserData;
import scheduler.rest.common.UserRoutes;

public class UserRegistration {

    @Test
    public void shouldRegisterUser() {
        UserData userData = DataGenerator.generateUserData();
        String requestBody = String.format(RestTestHelper.readJson(ResourcePath.USER_REGISTRATION_DATA_JSON),
                userData.getLogin(),
                userData.getName(),
                userData.getPassword()
        );

        Response response = RestTestHelper.doJsonPut(requestBody, UserRoutes.USER_REGISTRATION, HttpStatus.SC_OK);

        RegistrationResponse registrationResponse = response.as(RegistrationResponse.class);
    }
}
