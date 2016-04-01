package scheduler.core.converters;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import scheduler.core.entities.UserEntity;
import scheduler.core.models.SchedulerTask;
import scheduler.core.repositories.UserRepository;

import javax.inject.Inject;

@Service
public class SchedulerTaskEntityConverterImpl implements SchedulerTaskEntityConverter {

	private static final String ENTITY_MUST_NOT_BE_NULL = "Entity must not be null";
	private static final String USER_MUST_NOT_BE_NULL = "User must not be null";
	private static final String REMOTE_JOB_MUST_NOT_BE_NULL = "Remote job must not be null";

	@Inject
	private UserEntityConverter userEntityConverter;

	@Inject
	private UserRepository userRepository;

	@Inject
	private RemoteJobEntityConverter remoteJobEntityConverter;

	@Override
	public void populateEntity(final scheduler.core.entities.SchedulerTaskEntity entity, final SchedulerTask model) {
		Assert.notNull(entity, ENTITY_MUST_NOT_BE_NULL);
		Assert.notNull(entity.getRemoteJob(), REMOTE_JOB_MUST_NOT_BE_NULL);
		Assert.notNull(model, USER_MUST_NOT_BE_NULL);
		Assert.notNull(model.getRemoteJob(), REMOTE_JOB_MUST_NOT_BE_NULL);

		entity.setId(model.getId());
		entity.setUser(getUser(model.getId()));
		entity.setTaskType(model.getTaskType());
		entity.setTaskName(model.getTaskName());
		entity.setTaskDescription(model.getTaskDescription());
		entity.setTaskParametersJSON(model.getTaskParametersJSON());
		remoteJobEntityConverter.populateEntity(entity.getRemoteJob(), model.getRemoteJob());
	}

	@Override
	public SchedulerTask toModel(final scheduler.core.entities.SchedulerTaskEntity entity) {
		Assert.notNull(entity, ENTITY_MUST_NOT_BE_NULL);
		Assert.notNull(entity.getUser(), USER_MUST_NOT_BE_NULL);
		Assert.notNull(entity.getRemoteJob(), REMOTE_JOB_MUST_NOT_BE_NULL);

		SchedulerTask model = new SchedulerTask();
		model.setId(entity.getId());
		model.setUser(userEntityConverter.toModel(entity.getUser()));
		model.setTaskType(entity.getTaskType());
		model.setTaskName(entity.getTaskName());
		model.setTaskDescription(entity.getTaskDescription());
		model.setTaskParametersJSON(entity.getTaskParametersJSON());
		model.setRemoteJob(remoteJobEntityConverter.toModel(entity.getRemoteJob()));
		return model;
	}

	private UserEntity getUser(final Long userId) {
		return userRepository.findById(userId);
	}
}
