package scheduler.app.converters.entity;

import scheduler.app.entries.UserEntry;
import scheduler.app.models.User;

public class UserConverterImpl implements UserConverter {

    @Override
    public UserEntry populateEntity(final UserEntry entity, final User user) {
        entity.setId(user.getId());
        entity.setUsername(user.getUsername());
        return entity;
    }

    @Override
    public User toModel(final UserEntry userEntry) {
        User model = new User();
        model.setId(userEntry.getId());
        model.setUsername(userEntry.getUsername());
        return model;
    }
}
