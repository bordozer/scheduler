package scheduler.app.controllers.rest.scheduler.tasks.item.edit.services;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import scheduler.app.controllers.rest.scheduler.tasks.item.edit.converters.SchedulerTaskEditDtoConverter;
import scheduler.app.controllers.rest.scheduler.tasks.item.edit.dto.SchedulerTaskEditDto;
import scheduler.app.models.SchedulerTask;
import scheduler.app.models.User;
import scheduler.app.services.tasks.SchedulerTaskService;

import javax.inject.Inject;

@Service
public class SchedulerTaskEditServiceImpl implements SchedulerTaskEditService {

	private static final String ASSERT_USER_MUST_NOT_BE_NULL = "User must not be null";
	private static final String ASSERT_DTO_MUST_NOT_BE_NULL = "DTO must not be null";

	@Inject
	private SchedulerTaskService schedulerTaskService;

	@Inject
	private SchedulerTaskEditDtoConverter schedulerTaskEditDtoConverter;

	@Override
	public SchedulerTaskEditDto create(final User user, final SchedulerTaskEditDto editDTO) {
		Assert.notNull(user, ASSERT_USER_MUST_NOT_BE_NULL);
		Assert.notNull(editDTO, ASSERT_DTO_MUST_NOT_BE_NULL);

		SchedulerTask schedulerTask = schedulerTaskEditDtoConverter.toModel(user, editDTO);
		final SchedulerTask saved = schedulerTaskService.create(schedulerTask);
		return schedulerTaskEditDtoConverter.toDto(saved);
	}

	@Override
	public SchedulerTaskEditDto modify(final User user, final SchedulerTaskEditDto editDTO) {
		Assert.notNull(user, ASSERT_USER_MUST_NOT_BE_NULL);
		Assert.notNull(editDTO, ASSERT_DTO_MUST_NOT_BE_NULL);

		SchedulerTask schedulerTask = schedulerTaskEditDtoConverter.toModel(user, editDTO);
		SchedulerTask saved = schedulerTaskService.save(schedulerTask);
		return schedulerTaskEditDtoConverter.toDto(saved);
	}

	@Override
	public void delete(final User user, final Long schedulerTaskId) {
		Assert.notNull(user, ASSERT_USER_MUST_NOT_BE_NULL);

		if (schedulerTaskId == null || schedulerTaskId == 0) {
			return;
		}

		final SchedulerTask schedulerTask = schedulerTaskService.load(schedulerTaskId);
		if (!schedulerTask.getUser().equals(user)) {
			return;
		}

		schedulerTaskService.delete(schedulerTaskId);
	}
}
