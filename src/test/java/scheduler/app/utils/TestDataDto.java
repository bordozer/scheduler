package scheduler.app.utils;

import scheduler.app.dto.UserDto;

public class TestDataDto {

    public static UserDto user() {
        final UserDto model = new UserDto();
        model.setUserId(TestData.USER_ID);
        model.setUserName(TestData.USER_NAME);
        return model;
    }
}
