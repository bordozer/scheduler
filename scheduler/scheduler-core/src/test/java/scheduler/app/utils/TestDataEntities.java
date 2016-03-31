package scheduler.app.utils;

import scheduler.app.entities.RemoteJobEntity;
import scheduler.app.entities.SchedulerTaskEntity;
import scheduler.app.entities.UserEntity;
import scheduler.app.entities.UserSecureDetailsEntity;

public class TestDataEntities {

    public static UserEntity user() {
        final UserEntity user = new UserEntity();
        user.setId(TestData.USER_ID);
        user.setUsername(TestData.USER_NAME);
        user.setSecureDetails(userSecureDetails(user));
        return user;
    }

    public static UserSecureDetailsEntity userSecureDetails(final UserEntity user) {
        final UserSecureDetailsEntity secureDetails = new UserSecureDetailsEntity();
        secureDetails.setId(TestData.USER_SECURE_DETAILS_ID);
        secureDetails.setUser(user);
        secureDetails.setLogin(TestData.USER_LOGIN);
        secureDetails.setPassword(TestData.USER_PASSWORD);
        secureDetails.setRole(TestData.USER_ROLE);
        user.setSecureDetails(secureDetails);
        return secureDetails;
    }

    public static SchedulerTaskEntity schedulerTask(final UserEntity user) {
        final SchedulerTaskEntity schedulerTask = new SchedulerTaskEntity();
        schedulerTask.setId(TestData.SCHEDULER_TASK_ID);
        schedulerTask.setUser(user);
        schedulerTask.setTaskName(TestData.SCHEDULER_TASK_NAME);
        schedulerTask.setTaskDescription(TestData.SCHEDULER_TASK_DESCRIPTION);
        schedulerTask.setTaskType(TestData.SCHEDULER_TASK_TYPE);
        schedulerTask.setTaskParametersJSON(TestData.SCHEDULER_TASK_PARAMETERS_JSON);
        schedulerTask.setRemoteJob(remoteJob(schedulerTask));
        return schedulerTask;
    }

    public static RemoteJobEntity remoteJob(final SchedulerTaskEntity schedulerTaskEntity) {
        final RemoteJobEntity remoteJobEntity = new RemoteJobEntity();
        remoteJobEntity.setId(TestData.REMOTE_JOB_ID);
        remoteJobEntity.setRequestUrl(TestData.REMOTE_JOB_REQUEST_URL);
        remoteJobEntity.setRequestMethod(TestData.REMOTE_JOB_REQUEST_METHOD);
        remoteJobEntity.setAuthString(TestData.REMOTE_JOB_AUTH_STRING);
        remoteJobEntity.setPostJson(TestData.REMOTE_JOB_POST_JSON);
        remoteJobEntity.setSchedulerTask(schedulerTaskEntity);
        schedulerTaskEntity.setRemoteJob(remoteJobEntity);
        return remoteJobEntity;
    }

    public static UserSecureDetailsEntity userSecureDetails() {
        return userSecureDetails(user());
    }

    public static SchedulerTaskEntity schedulerTask() {
        return schedulerTask(user());
    }

    public static RemoteJobEntity remoteJob() {
        return remoteJob(schedulerTask(user()));
    }
}
