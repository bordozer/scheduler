package scheduler.app.converters.entity;

import org.springframework.stereotype.Service;
import scheduler.app.entities.SchedulerTaskEntity;
import scheduler.app.entities.UserEntity;
import scheduler.app.models.SchedulerTask;
import scheduler.app.repositories.UserRepository;

import javax.inject.Inject;

@Service
public class SchedulerTaskEntityConverterImpl implements SchedulerTaskEntityConverter {

	@Inject
	private UserEntityConverter userEntityConverter;

	@Inject
	private UserRepository userRepository;

	@Inject
	private RemoteJobEntityConverter remoteJobEntityConverter;

	@Override
	public void populateEntity(final SchedulerTaskEntity entity, final SchedulerTask model) {
		entity.setId(model.getId());
		entity.setUser(getUser(model.getId()));
		entity.setTaskType(model.getTaskType());
		entity.setTaskName(model.getTaskName());
		entity.setTaskDescription(model.getTaskDescription());
		entity.setTaskParametersJSON(model.getTaskParametersJSON());
		remoteJobEntityConverter.populateEntity(entity.getRemoteJob(), model.getRemoteJob());
	}

	@Override
	public SchedulerTask toModel(final SchedulerTaskEntity entity) {
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
