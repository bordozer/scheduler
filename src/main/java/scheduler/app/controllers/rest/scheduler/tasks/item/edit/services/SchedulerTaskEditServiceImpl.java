package scheduler.app.controllers.rest.scheduler.tasks.item.edit.services;

import org.springframework.stereotype.Service;
import scheduler.app.controllers.rest.scheduler.tasks.item.edit.dto.SchedulerTaskEditDto;
import scheduler.app.controllers.rest.scheduler.tasks.item.edit.converters.SchedulerTaskEditDtoConverter;
import scheduler.app.models.SchedulerTask;
import scheduler.app.models.User;
import scheduler.app.services.tasks.SchedulerTaskService;

import javax.inject.Inject;

@Service
public class SchedulerTaskEditServiceImpl implements SchedulerTaskEditService {

	@Inject
	private SchedulerTaskService schedulerTaskService;

	@Inject
	private SchedulerTaskEditDtoConverter schedulerTaskEditDtoConverter;

	@Override
	public SchedulerTaskEditDto create(final User user, final SchedulerTaskEditDto editDTO) {
		SchedulerTask schedulerTask = schedulerTaskEditDtoConverter.toModel(user, editDTO);
		final SchedulerTask saved = schedulerTaskService.create(schedulerTask);
		return schedulerTaskEditDtoConverter.toDto(saved);
	}

	@Override
	public SchedulerTaskEditDto modify(final User user, final SchedulerTaskEditDto editDTO) {
		SchedulerTask schedulerTask = schedulerTaskEditDtoConverter.toModel(user, editDTO);
		SchedulerTask saved = schedulerTaskService.save(schedulerTask);
		return schedulerTaskEditDtoConverter.toDto(saved);
	}

	@Override
	public void delete(final User user, final Long schedulerTaskId) {
		if (schedulerTaskId == 0) {
			return;
		}
		schedulerTaskService.delete(schedulerTaskId);
	}
}
