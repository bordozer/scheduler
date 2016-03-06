package scheduler.app.utils;

import scheduler.app.models.RemoteJob;
import scheduler.app.models.User;
import scheduler.app.models.UserSecureDetails;

public class TestDataModels {

    public static User user() {
        final User model = new User();
        model.setId(TestData.USER_ID);
        model.setUsername(TestData.USER_NAME);
        return model;
    }

    public static RemoteJob remoteJob() {
        final RemoteJob model = new RemoteJob();
        model.setId(TestData.REMOTE_JOB_ID);
        model.setRequestUrl(TestData.REMOTE_JOB_REQUEST_URL);
        model.setRequestMethod(TestData.REMOTE_JOB_REQUEST_METHOD);
        model.setAuthString(TestData.REMOTE_JOB_AUTH_STRING);
        model.setPostJson(TestData.REMOTE_JOB_POST_JSON);
        return model;
    }

    public static UserSecureDetails userSecureDetails(final User user) {
        final UserSecureDetails model = new UserSecureDetails();
        model.setId(TestData.USER_SECURE_DETAILS_ID);
        model.setLogin(TestData.USER_LOGIN);
        model.setPasswordEncrypted(TestData.USER_PASSWORD);
        model.setRole(TestData.USER_ROLE);
        model.setUser(user);
        return model;
    }
}
