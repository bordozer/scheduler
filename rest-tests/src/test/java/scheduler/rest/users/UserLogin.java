package scheduler.rest.users;

import com.jayway.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import scheduler.rest.common.AuthResponse;
import scheduler.rest.common.DataGenerator;
import scheduler.rest.common.RestTestHelper;
import scheduler.rest.common.UserData;
import scheduler.rest.common.routes.SchedulerTaskRoutes;
import scheduler.rest.dto.RegistrationResponse;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserLogin {

    private static final String UNAUTHORIZED = "Unauthorized";
    private static final String FULL_AUTHENTICATION_IS_REQUIRED_TO_ACCESS_THIS_RESOURCE = "Full authentication is required to access this resource";
    private static final String AUTHENTICATION_FAILURE = "Authentication failure";

    private UserData userData = DataGenerator.generateUserData();

    @Test
    public void shouldFailNonPublicResourcesGettingIfUnauthorized() {
        Response response = RestTestHelper.doGet(SchedulerTaskRoutes.SCHEDULER_TASK_LIST, HttpStatus.SC_UNAUTHORIZED);
        AuthResponse authResponse = response.as(AuthResponse.class);
        assertThat(authResponse.getResponseCode(), is(HttpStatus.SC_UNAUTHORIZED));
        assertThat(authResponse.getDetails().get(AuthResponse.AUTH_RESULT), is(UNAUTHORIZED));
        assertThat(authResponse.getDetails().get(AuthResponse.ERROR), is(FULL_AUTHENTICATION_IS_REQUIRED_TO_ACCESS_THIS_RESOURCE));
    }

    @Test
    public void shouldFailLoginIfCredentialsAreWrong() {
        Response response = RestTestHelper.login(userData.getLogin(), userData.getPassword(), HttpStatus.SC_UNAUTHORIZED);
        AuthResponse authResponse = response.as(AuthResponse.class);
        assertThat(authResponse.getResponseCode(), is(HttpStatus.SC_UNAUTHORIZED));
        assertThat(authResponse.getDetails().get(AuthResponse.AUTH_RESULT), is(AUTHENTICATION_FAILURE));
    }

    @Test
    public void shouldGoThrough() {
        // register new user
        Response userRegistrationResponse = RestTestHelper.register(userData.getLogin(), userData.getPassword(), userData.getName());

        // registration of new user is not a authentication - non public resources are not available
        Response taskListResponse2 = RestTestHelper.doGet(SchedulerTaskRoutes.SCHEDULER_TASK_LIST, HttpStatus.SC_UNAUTHORIZED);
        AuthResponse taskList2AuthResponse = taskListResponse2.as(AuthResponse.class);
        assertThat(taskList2AuthResponse.getResponseCode(), is(HttpStatus.SC_UNAUTHORIZED));
        assertThat(taskList2AuthResponse.getDetails().get(AuthResponse.AUTH_RESULT), is(UNAUTHORIZED));

        // login as earlie registered user
        Response loginResponse2 = RestTestHelper.login(userData.getLogin(), userData.getPassword(), HttpStatus.SC_OK);

        // non public resources should be available now
        Response taskListResponse3 = RestTestHelper.doGet(SchedulerTaskRoutes.SCHEDULER_TASK_LIST, HttpStatus.SC_OK);
        List<RegistrationResponse> schedulerTasks = taskListResponse3.as(List.class);
        // 've just registered user does not have tasks yet
        assertThat(schedulerTasks.size(), is(0));

        // log out
        Response logoutResponse = RestTestHelper.logout();
        AuthResponse logoutAuthResponse = logoutResponse.as(AuthResponse.class);
        assertThat(logoutAuthResponse.getResponseCode(), is(HttpStatus.SC_OK));

        // non public resources are not available again
        Response taskListResponse4 = RestTestHelper.doGet(SchedulerTaskRoutes.SCHEDULER_TASK_LIST, HttpStatus.SC_UNAUTHORIZED);
        AuthResponse taskList4AuthResponse = taskListResponse4.as(AuthResponse.class);
        assertThat(taskList4AuthResponse.getResponseCode(), is(HttpStatus.SC_UNAUTHORIZED));
    }
}
