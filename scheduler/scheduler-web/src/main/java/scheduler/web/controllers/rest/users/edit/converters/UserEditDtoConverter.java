package scheduler.web.controllers.rest.users.edit.converters;

import scheduler.core.models.User;
import scheduler.web.controllers.rest.users.edit.dto.NewUserDto;

public interface UserEditDtoConverter {

    User createUser(NewUserDto dto);
}
