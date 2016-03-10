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
        Response loginResponse1 = RestTestHelper.login(userData.getLogin(), userData.getPassword(), HttpStatus.SC_UNAUTHORIZED);
        Response registerResponse = RestTestHelper.register(userData.getLogin(), userData.getPassword(), userData.getName());
        Response taskListResponse2 = RestTestHelper.doGet(SchedulerTaskRoutes.SCHEDULER_TASK_LIST, HttpStatus.SC_UNAUTHORIZED);
        Response loginResponse2 = RestTestHelper.login(userData.getLogin(), userData.getPassword(), HttpStatus.SC_OK);
        Response taskListResponse3 = RestTestHelper.doGet(SchedulerTaskRoutes.SCHEDULER_TASK_LIST, HttpStatus.SC_OK);
        Response logoutResponse = RestTestHelper.logout();
        Response taskListResponse4 = RestTestHelper.doGet(SchedulerTaskRoutes.SCHEDULER_TASK_LIST, HttpStatus.SC_UNAUTHORIZED);
    }
}
