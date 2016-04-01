package utils;

import schemway.core.models.RemoteJob;
import schemway.core.models.SchedulerTask;
import schemway.core.models.User;
import schemway.core.models.UserSecureDetails;

public class TestDataModels {

    public static User user() {
        final User model = new User();
        model.setId(TestData.USER_ID);
        model.setUsername(TestData.USER_NAME);
        return model;
    }

    public static User currentUser() {
        final User model = new User();
        model.setId(TestData.CURRENT_USER_ID);
        model.setUsername(TestData.CURRENT_USER_NAME);
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

    public static SchedulerTask schedulerTask() {
        final SchedulerTask model = new SchedulerTask();
        model.setId(TestData.SCHEDULER_TASK_ID);
        model.setTaskName(TestData.SCHEDULER_TASK_NAME);
        model.setTaskType(TestData.SCHEDULER_TASK_TYPE);
        model.setTaskDescription(TestData.SCHEDULER_TASK_DESCRIPTION);
        model.setTaskParametersJSON(TestData.SCHEDULER_TASK_PARAMETERS_JSON);
        model.setUser(user());
        model.setRemoteJob(remoteJob());
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
}
