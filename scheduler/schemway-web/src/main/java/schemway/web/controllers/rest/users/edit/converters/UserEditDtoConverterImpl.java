package schemway.web.controllers.rest.users.edit.converters;

import org.springframework.stereotype.Service;
import schemway.core.models.User;
import schemway.core.models.UserRole;
import schemway.core.models.UserSecureDetails;
import schemway.core.services.users.UserService;
import schemway.web.controllers.rest.users.edit.dto.NewUserDto;

import javax.inject.Inject;

@Service
public class UserEditDtoConverterImpl implements UserEditDtoConverter {

    @Inject
    private UserService userService;

    @Override
    public User createUser(final NewUserDto dto) {
        User user = toModel(dto);
        UserSecureDetails userSecureDetails = toUserSecureDetailsModel(user, dto);
        return userService.create(user, userSecureDetails);
    }

    private User toModel(final NewUserDto dto) {
        User model = new User();
        model.setId(dto.getUserId());
        model.setUsername(dto.getName());
        return model;
    }

    private UserSecureDetails toUserSecureDetailsModel(final User user, final NewUserDto editDTO) {
        UserSecureDetails details = new UserSecureDetails();
        details.setUser(user);
        details.setLogin(editDTO.getLogin());
        details.setRole(UserRole.USER);
        details.setPasswordEncrypted(editDTO.getPassword());
        return details;
    }
}
