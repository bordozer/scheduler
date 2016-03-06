package scheduler.app.utils;

import scheduler.app.models.RemoteJob;
import scheduler.app.models.User;

public class TestDataModels {

    public static User user() {
        final User ret = new User();
        ret.setId(TestData.USER_ID);
        ret.setUsername(TestData.USER_NAME);
        return ret;
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
}
