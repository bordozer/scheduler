package scheduler.app.controllers.rest.users.edit.converters;

import scheduler.app.controllers.rest.users.edit.dto.NewUserDto;
import scheduler.app.models.User;
import scheduler.app.models.UserSecureDetails;

public interface UserEditDtoConverter {

    User createUser(NewUserDto dto);
}
