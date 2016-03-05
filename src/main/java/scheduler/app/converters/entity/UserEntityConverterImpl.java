package scheduler.app.converters.entity;

import org.springframework.stereotype.Service;
import scheduler.app.entities.UserEntity;
import scheduler.app.models.User;

@Service
public class UserEntityConverterImpl implements UserEntityConverter {

	@Override
	public void populateEntity(final UserEntity entity, final User user) {
		entity.setId(user.getId());
		entity.setUsername(user.getUsername());
	}

	@Override
	public User toModel(final UserEntity userEntity) {
		User model = new User();
		model.setId(userEntity.getId());
		model.setUsername(userEntity.getUsername());
		return model;
	}
}
