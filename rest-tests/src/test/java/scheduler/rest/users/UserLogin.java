package scheduler.rest.users;

import com.jayway.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import scheduler.rest.common.DataGenerator;
import scheduler.rest.common.RestTestHelper;
import scheduler.rest.common.UserData;
import scheduler.rest.common.routes.SchedulerTaskRoutes;

public class UserLogin {

    @Test
    public void shouldLoginUser() {
        UserData userData = DataGenerator.generateUserData();

        Response taskListResponse1 = RestTestHelper.doGet(SchedulerTaskRoutes.SCHEDULER_TASK_LIST, HttpStatus.SC_UNAUTHORIZED);
//        assertEquals(HttpStatus.SC_UNAUTHORIZED, taskListResponse1.getStatusCode());

        Response loginResponse1 = RestTestHelper.login(userData.getLogin(), userData.getPassword(), HttpStatus.SC_UNAUTHORIZED);
//        assertEquals(HttpStatus.SC_UNAUTHORIZED, loginResponse1.getStatusCode());

        Response registerResponse = RestTestHelper.register(userData.getLogin(), userData.getPassword(), userData.getName());
//        assertEquals(HttpStatus.SC_OK, registerResponse.getStatusCode());

        Response taskListResponse2 = RestTestHelper.doGet(SchedulerTaskRoutes.SCHEDULER_TASK_LIST, HttpStatus.SC_UNAUTHORIZED);
//        assertEquals(HttpStatus.SC_UNAUTHORIZED, taskListResponse2.getStatusCode());

        Response loginResponse2 = RestTestHelper.login(userData.getLogin(), userData.getPassword(), HttpStatus.SC_OK);
//        assertEquals(HttpStatus.SC_OK, loginResponse2.getStatusCode());

        Response taskListResponse3 = RestTestHelper.doGet(SchedulerTaskRoutes.SCHEDULER_TASK_LIST, HttpStatus.SC_OK);
//        assertEquals(HttpStatus.SC_OK, taskListResponse3.getStatusCode());

        Response logoutResponse = RestTestHelper.logout();
//        assertEquals(HttpStatus.SC_OK, logoutResponse.getStatusCode());

        Response taskListResponse4 = RestTestHelper.doGet(SchedulerTaskRoutes.SCHEDULER_TASK_LIST, HttpStatus.SC_UNAUTHORIZED);
//        assertEquals(HttpStatus.SC_UNAUTHORIZED, taskListResponse4.getStatusCode());
    }
}
