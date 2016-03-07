package scheduler.app.converters.entity;

import scheduler.app.entities.UserEntity;
import scheduler.app.entities.UserSecureDetailsEntity;
import scheduler.app.models.UserSecureDetails;

public interface UserSecureDetailsConverter extends GenericEntityConverter<UserSecureDetailsEntity, UserSecureDetails> {

    void createEntity(UserEntity userEntity, UserSecureDetails model);
}
