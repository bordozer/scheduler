package scheduler.core.services.tasks;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import scheduler.core.converters.entity.SchedulerTaskEntityConverter;
import scheduler.core.entities.RemoteJobEntity;
import scheduler.core.entities.SchedulerTaskEntity;
import scheduler.core.exceptions.EntityNotFoundException;
import scheduler.core.models.SchedulerTask;
import scheduler.core.repositories.SchedulerTaskRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchedulerTaskServiceImpl implements SchedulerTaskService {

	private static final String USER_ID_MUST_NOT_BE_NULL = "User ID must not be null";
	private static final String SCHEDULER_TASK_ID_MUST_NOT_BE_NULL = "Scheduler task ID must not be null";
	private static final String MODEL_MUST_NOT_BE_NULL = "Model must not be null";
	private static final String REMOTE_JOB_MUST_NOT_BE_NULL = "Remote job must not be null";

	@Inject
	private SchedulerTaskRepository schedulerTaskRepository;

	@Inject
	private SchedulerTaskEntityConverter schedulerTaskEntityConverter;

	@Override
	public List<SchedulerTask> loadAll() {
		return toModel(schedulerTaskRepository.findAll());
	}

	@Override
	public List<SchedulerTask> loadAll(final Long userId) {
		Assert.notNull(userId, USER_ID_MUST_NOT_BE_NULL);

		return toModel(schedulerTaskRepository.findAllByUserId(userId));
	}

	@Override
	public SchedulerTask load(final Long userId, final Long schedulerTaskId) {
		Assert.notNull(userId, USER_ID_MUST_NOT_BE_NULL);
		Assert.notNull(schedulerTaskId, SCHEDULER_TASK_ID_MUST_NOT_BE_NULL);

		return schedulerTaskEntityConverter.toModel(schedulerTaskRepository.findOne(schedulerTaskId));
	}

	@Override
	public SchedulerTask create(final Long userId, final SchedulerTask schedulerTask) {
		Assert.notNull(userId, USER_ID_MUST_NOT_BE_NULL);
		Assert.notNull(schedulerTask, MODEL_MUST_NOT_BE_NULL);
		Assert.notNull(schedulerTask.getRemoteJob(), REMOTE_JOB_MUST_NOT_BE_NULL);

		SchedulerTaskEntity schedulerTaskEntity = new SchedulerTaskEntity();
		RemoteJobEntity remoteJob = new RemoteJobEntity();
		schedulerTaskEntity.setRemoteJob(remoteJob);
		remoteJob.setSchedulerTask(schedulerTaskEntity);

		return populateAndSave(schedulerTaskEntity, schedulerTask);
	}

	@Override
	public SchedulerTask modify(final Long userId, final SchedulerTask schedulerTask) {
		Assert.notNull(userId, USER_ID_MUST_NOT_BE_NULL);
		Assert.notNull(schedulerTask, MODEL_MUST_NOT_BE_NULL);
		Assert.notNull(schedulerTask.getRemoteJob(), REMOTE_JOB_MUST_NOT_BE_NULL);

		SchedulerTaskEntity schedulerTaskEntity = schedulerTaskRepository.findByUserIdAndId(userId, schedulerTask.getId());
		if (schedulerTaskEntity == null) {
			throw new EntityNotFoundException(String.format("Scheduler Task #%d cannot be modified: not found (user %d)",
					userId, schedulerTask.getId()));
		}

		return populateAndSave(schedulerTaskEntity, schedulerTask);
	}

	@Override
	public List<SchedulerTaskEntity> delete(final Long userId, final Long taskId) {
		return schedulerTaskRepository.deleteAllByUserIdAndId(userId, taskId);
	}

	private List<SchedulerTask> toModel(final List<SchedulerTaskEntity> allByUserId) {
		return allByUserId.stream()
				.map(schedulerTaskEntityConverter::toModel)
				.collect(Collectors.toList());
	}

	private SchedulerTask populateAndSave(final SchedulerTaskEntity taskEntity, final SchedulerTask schedulerTask) {
		schedulerTaskEntityConverter.populateEntity(taskEntity, schedulerTask);
		SchedulerTaskEntity savedEntity = schedulerTaskRepository.saveAndFlush(taskEntity);
		return schedulerTaskEntityConverter.toModel(savedEntity);
	}
}
