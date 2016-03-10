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
        assertEquals(401, taskListResponse1.getStatusCode());

        Response loginResponse = RestTestHelper.login(userData.getLogin(), userData.getPassword());
        assertEquals(200, loginResponse.getStatusCode());

        Response taskListResponse2 = RestTestHelper.doGet(SchedulerTaskRoutes.SCHEDULER_TASK_LIST, HttpStatus.SC_OK);
        assertEquals(200, taskListResponse2.getStatusCode());

        Response logoutResponse = RestTestHelper.logout();
        assertEquals(200, logoutResponse.getStatusCode());

        Response taskListResponse3 = RestTestHelper.doGet(SchedulerTaskRoutes.SCHEDULER_TASK_LIST, HttpStatus.SC_UNAUTHORIZED);
        assertEquals(401, taskListResponse3.getStatusCode());
    }
}
