package schemway.web.controllers.rest.scheduler.tasks.item.edit.services;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import schemway.core.models.SchedulerTask;
import schemway.core.models.User;
import schemway.core.services.tasks.SchedulerTaskService;
import schemway.web.controllers.rest.scheduler.tasks.item.edit.converters.SchedulerTaskEditDtoConverter;
import schemway.web.controllers.rest.scheduler.tasks.item.edit.dto.SchedulerTaskEditDto;

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
		final SchedulerTask saved = schedulerTaskService.create(user.getId(), schedulerTask);
		return schedulerTaskEditDtoConverter.toDto(saved);
	}

	@Override
	public SchedulerTaskEditDto modify(final User user, final SchedulerTaskEditDto editDTO) {
		Assert.notNull(user, ASSERT_USER_MUST_NOT_BE_NULL);
		Assert.notNull(editDTO, ASSERT_DTO_MUST_NOT_BE_NULL);

		SchedulerTask schedulerTask = schedulerTaskEditDtoConverter.toModel(user, editDTO);
		SchedulerTask saved = schedulerTaskService.modify(user.getId(), schedulerTask);
		return schedulerTaskEditDtoConverter.toDto(saved);
	}

	@Override
	public void delete(final User user, final Long schedulerTaskId) {
		Assert.notNull(user, ASSERT_USER_MUST_NOT_BE_NULL);

		if (schedulerTaskId == null || schedulerTaskId == 0) {
			return;
		}

		final SchedulerTask schedulerTask = schedulerTaskService.load(user.getId(), schedulerTaskId);
		if (!schedulerTask.getUser().equals(user)) {
			return;
		}

		schedulerTaskService.delete(user.getId(), schedulerTaskId);
	}
}
