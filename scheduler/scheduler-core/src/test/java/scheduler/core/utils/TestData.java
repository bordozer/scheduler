package scheduler.core.utils;

import scheduler.core.models.RequestMethod;
import scheduler.core.models.SchedulerTaskType;
import scheduler.core.models.UserRole;

public class TestData {

    public static final Long USER_ID = 33L;
    public static final String USER_NAME = "User name";

    public static final Long CURRENT_USER_ID = 55L;
    public static final String CURRENT_USER_NAME = "Current user name";

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
    public static final String REMOTE_JOB_REQUEST_URL = "nba.com";
    public static final RequestMethod REMOTE_JOB_REQUEST_METHOD = RequestMethod.POST;
    public static final String REMOTE_JOB_AUTH_STRING = "ABCDE";
    public static final String REMOTE_JOB_POST_JSON = "{value:111}";
}
