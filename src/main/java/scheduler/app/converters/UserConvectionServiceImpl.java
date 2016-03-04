package scheduler.app.converters;

import scheduler.app.entries.UserEntry;
import scheduler.app.models.User;

public class UserConvectionServiceImpl implements UserConvectionService {

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
