package scheduler.app.controllers.rest.users.edit.converters;

import org.springframework.stereotype.Service;
import scheduler.app.controllers.rest.users.edit.dto.NewUserDto;
import scheduler.app.models.User;
import scheduler.app.models.UserRole;
import scheduler.app.models.UserSecureDetails;

@Service
public class UserEditDtoConverterImpl implements UserEditDtoConverter {

    @Override
    public User toModel(final NewUserDto dto) {
        User model = new User();
        model.setId(dto.getUserId());
        model.setUsername(dto.getName());
        return model;
    }

    @Override
    public UserSecureDetails toUserSecureDetailsModel(final User user, final NewUserDto editDTO) {
        UserSecureDetails details = new UserSecureDetails();
        details.setUser(user);
        details.setLogin(editDTO.getLogin());
        details.setRole(UserRole.USER);
        details.setPasswordEncrypted(editDTO.getPassword());
        return details;
    }
}
