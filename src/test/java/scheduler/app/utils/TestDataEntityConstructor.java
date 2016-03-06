package scheduler.app.utils;

import org.springframework.web.bind.annotation.RequestMethod;
import scheduler.app.entities.RemoteJobEntity;
import scheduler.app.entities.SchedulerTaskEntity;
import scheduler.app.entities.UserEntity;
import scheduler.app.entities.UserSecureDetailsEntity;
import scheduler.app.models.SchedulerTaskType;
import scheduler.app.models.UserRole;

public class TestDataEntityConstructor {

    public static final Long USER_ID = 33L;
    public static final String USER_NAME = "User name";

    public static final Long USER_SECURE_DETAILS_ID = 55L;
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final UserRole USER_ROLE = UserRole.USER;

    public static final Long SCHEDULER_TASK_ID = 222L;
    public static final String SCHEDULER_TASK_NAME = "Scheduler task name";
    public static final SchedulerTaskType SCHEDULER_TASK_TYPE = SchedulerTaskType.DAILY;
    public static final String SCHEDULER_TASK_DESCRIPTION = "Scheduler task description";
    public static final String SCHEDULER_TASK_PARAMETERS_JSON = "{id:12, value:44}";

    public static final Long REMOTE_JOB_ID = 111L;
    public static final String REQUEST_URL = "nba.com";
    public static final RequestMethod REQUEST_METHOD = RequestMethod.POST;
    public static final String AUTH_STRING = "ABCDE";
    public static final String POST_JSON = "{value:111}";

    public static UserEntity userEntity() {
        final UserEntity user = new UserEntity();
        user.setId(USER_ID);
        user.setUsername(USER_NAME);
        user.setSecureDetails(userSecureDetailsEntity(user));
        return user;
    }

    public static UserSecureDetailsEntity userSecureDetailsEntity(final UserEntity user) {
        final UserSecureDetailsEntity secureDetails = new UserSecureDetailsEntity();
        secureDetails.setId(USER_SECURE_DETAILS_ID);
        secureDetails.setUser(user);
        secureDetails.setLogin(USER_LOGIN);
        secureDetails.setPassword(USER_PASSWORD);
        secureDetails.setRole(USER_ROLE);
        user.setSecureDetails(secureDetails);
        return secureDetails;
    }

    public static SchedulerTaskEntity schedulerTaskEntity(final UserEntity user) {
        final SchedulerTaskEntity schedulerTask = new SchedulerTaskEntity();
        schedulerTask.setId(SCHEDULER_TASK_ID);
        schedulerTask.setUser(user);
        schedulerTask.setTaskName(SCHEDULER_TASK_NAME);
        schedulerTask.setTaskDescription(SCHEDULER_TASK_DESCRIPTION);
        schedulerTask.setTaskType(SCHEDULER_TASK_TYPE);
        schedulerTask.setTaskParametersJSON(SCHEDULER_TASK_PARAMETERS_JSON);
        schedulerTask.setRemoteJob(remoteJobEntity(schedulerTask));
        return schedulerTask;
    }

    public static RemoteJobEntity remoteJobEntity(final SchedulerTaskEntity schedulerTaskEntity) {
        final RemoteJobEntity remoteJobEntity = new RemoteJobEntity();
        remoteJobEntity.setId(REMOTE_JOB_ID);
        remoteJobEntity.setRequestUrl(REQUEST_URL);
        remoteJobEntity.setRequestMethod(REQUEST_METHOD);
        remoteJobEntity.setAuthString(AUTH_STRING);
        remoteJobEntity.setPostJson(POST_JSON);
        remoteJobEntity.setSchedulerTask(schedulerTaskEntity);
        schedulerTaskEntity.setRemoteJob(remoteJobEntity);
        return remoteJobEntity;
    }

}
