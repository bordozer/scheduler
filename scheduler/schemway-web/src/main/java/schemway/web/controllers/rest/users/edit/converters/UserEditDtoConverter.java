package schemway.web.controllers.rest.users.edit.converters;

import schemway.core.models.User;
import schemway.web.controllers.rest.users.edit.dto.NewUserDto;

public interface UserEditDtoConverter {

    User createUser(NewUserDto dto);
}
