package scheduler.app.utils;

import scheduler.app.dto.RemoteJobDto;
import scheduler.app.dto.UserDto;

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

    public static RemoteJobDto remoteJob() {
        final RemoteJobDto model = new RemoteJobDto();
        model.setId(TestData.REMOTE_JOB_ID);
        model.setRequestUrl(TestData.REMOTE_JOB_REQUEST_URL);
        model.setRequestMethod(TestData.REMOTE_JOB_REQUEST_METHOD);
        model.setAuthString(TestData.REMOTE_JOB_AUTH_STRING);
        model.setPostJson(TestData.REMOTE_JOB_POST_JSON);
        return model;
    }
}
