package scheduler.app.converters.dto;

import org.springframework.stereotype.Service;
import scheduler.app.dto.UserDto;
import scheduler.app.models.User;

import java.util.function.Function;

@Service
public class UserDtoConverter extends AbstractToDtoConverter<User, UserDto> {

	@Override
	public User toModel(final User user, final UserDto dto) {
		final User model = new User();
		model.setId(dto.getUserId());
		model.setUsername(dto.getUserName());
		return model;
	}

	@Override
	protected Function<User, UserDto> taskMapper() {
		return model -> {
			final UserDto dto = new UserDto();
			dto.setUserId(model.getId());
			dto.setUserName(model.getUsername());
			return dto;
		};
	}
}
