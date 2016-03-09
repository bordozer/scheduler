package scheduler.rest.users;

import org.testng.annotations.Test;
import scheduler.rest.common.RestTestHelper;
import scheduler.rest.common.UserData;

public class UserLogin {

    @Test
    public void shouldLoginUser() {
        UserData userData = RestTestHelper.generateAndLoginUser();
        RestTestHelper.logout();
        RestTestHelper.login(userData.getLogin(), userData.getPassword());
    }
}
