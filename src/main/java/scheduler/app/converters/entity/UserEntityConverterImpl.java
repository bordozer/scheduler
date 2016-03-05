package scheduler.app.converters.entity;

import org.springframework.stereotype.Service;
import scheduler.app.entities.UserEntry;
import scheduler.app.models.User;

@Service
public class UserEntityConverterImpl implements UserEntityConverter {

	@Override
	public void populateEntity(final UserEntry entity, final User user) {
		entity.setId(user.getId());
		entity.setUsername(user.getUsername());
	}

	@Override
	public User toModel(final UserEntry userEntry) {
		User model = new User();
		model.setId(userEntry.getId());
		model.setUsername(userEntry.getUsername());
		return model;
	}
}
