package scheduler.rest.users;

import com.jayway.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import scheduler.rest.common.RestTestHelper;
import scheduler.rest.common.UserData;
import scheduler.rest.common.routes.SchedulerTaskRoutes;

import static org.junit.Assert.assertEquals;

public class UserLogin {

    @Test
    public void shouldLoginUser() {
        UserData userData = RestTestHelper.generateAndLoginUser();

        Response taskListResponse1 = RestTestHelper.doGet(SchedulerTaskRoutes.SCHEDULER_TASK_LIST, HttpStatus.SC_UNAUTHORIZED);
        assertEquals(HttpStatus.SC_UNAUTHORIZED, taskListResponse1.getStatusCode());

        Response loginResponse = RestTestHelper.login(userData.getLogin(), userData.getPassword(), HttpStatus.SC_UNAUTHORIZED);
        assertEquals(HttpStatus.SC_UNAUTHORIZED, loginResponse.getStatusCode());

        Response registerResponse = RestTestHelper.register(userData.getLogin(), userData.getPassword(), userData.getName());
        assertEquals(HttpStatus.SC_OK, registerResponse.getStatusCode());

        Response taskListResponse2 = RestTestHelper.doGet(SchedulerTaskRoutes.SCHEDULER_TASK_LIST, HttpStatus.SC_OK);
        assertEquals(HttpStatus.SC_OK, taskListResponse2.getStatusCode());

        Response logoutResponse = RestTestHelper.logout();
        assertEquals(HttpStatus.SC_OK, logoutResponse.getStatusCode());

        Response taskListResponse3 = RestTestHelper.doGet(SchedulerTaskRoutes.SCHEDULER_TASK_LIST, HttpStatus.SC_UNAUTHORIZED);
        assertEquals(HttpStatus.SC_UNAUTHORIZED, taskListResponse3.getStatusCode());
    }
}
