package utils;

import schemway.web.controllers.rest.scheduler.tasks.item.edit.dto.RemoteJobEditDto;
import schemway.web.controllers.rest.scheduler.tasks.item.edit.dto.SchedulerTaskEditDto;
import schemway.web.dto.RemoteJobDto;
import schemway.web.dto.SchedulerTaskDto;
import schemway.web.dto.UserDto;

public class TestDataDto {

    public static UserDto user() {
        final UserDto model = new UserDto();
        model.setUserId(TestData.USER_ID);
        model.setUserName(TestData.USER_NAME);
        return model;
    }

    public static UserDto currentUser() {
        final UserDto model = new UserDto();
        model.setUserId(TestData.CURRENT_USER_ID);
        model.setUserName(TestData.CURRENT_USER_NAME);
        return model;
    }

    public static SchedulerTaskDto schedulerTask() {
        final SchedulerTaskDto model = new SchedulerTaskDto();
        model.setTaskId(TestData.SCHEDULER_TASK_ID);
        model.setTaskName(TestData.SCHEDULER_TASK_NAME);
        model.setTaskType(TestData.SCHEDULER_TASK_TYPE);
        model.setTaskDescription(TestData.SCHEDULER_TASK_DESCRIPTION);
        model.setTaskParametersJSON(TestData.SCHEDULER_TASK_PARAMETERS_JSON);
        model.setUser(user());
        model.setRemoteJob(remoteJob());
        return model;
    }

    public static RemoteJobDto remoteJob() {
        final RemoteJobDto model = new RemoteJobDto();
        model.setRemoteJobId(TestData.REMOTE_JOB_ID);
        model.setRequestUrl(TestData.REMOTE_JOB_REQUEST_URL);
        model.setRequestMethod(TestData.REMOTE_JOB_REQUEST_METHOD);
        model.setAuthString(TestData.REMOTE_JOB_AUTH_STRING);
        model.setPostJson(TestData.REMOTE_JOB_POST_JSON);
        return model;
    }

    public static SchedulerTaskEditDto schedulerTaskEdit() {
        final SchedulerTaskEditDto model = new SchedulerTaskEditDto();
        model.setTaskId(TestData.SCHEDULER_TASK_ID);
        model.setTaskName(TestData.SCHEDULER_TASK_NAME);
        model.setTaskType(TestData.SCHEDULER_TASK_TYPE);
        model.setTaskDescription(TestData.SCHEDULER_TASK_DESCRIPTION);
        model.setTaskParametersJSON(TestData.SCHEDULER_TASK_PARAMETERS_JSON);
        model.setRemoteJob(remoteJobEdit());
        return model;
    }

    public static RemoteJobEditDto remoteJobEdit() {
        final RemoteJobEditDto model = new RemoteJobEditDto();
        model.setRemoteJobId(TestData.REMOTE_JOB_ID);
        model.setRequestUrl(TestData.REMOTE_JOB_REQUEST_URL);
        model.setRequestMethod(TestData.REMOTE_JOB_REQUEST_METHOD);
        model.setAuthString(TestData.REMOTE_JOB_AUTH_STRING);
        model.setPostJson(TestData.REMOTE_JOB_POST_JSON);
        return model;
    }
}
