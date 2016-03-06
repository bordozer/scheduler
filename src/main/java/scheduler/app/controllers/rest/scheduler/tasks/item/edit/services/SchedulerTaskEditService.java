package scheduler.app.controllers.rest.scheduler.tasks.item.edit.services;

import scheduler.app.controllers.rest.scheduler.tasks.item.edit.dto.SchedulerTaskEditDto;
import scheduler.app.models.User;

public interface SchedulerTaskEditService {

	SchedulerTaskEditDto create(User user, SchedulerTaskEditDto editDTO);

	SchedulerTaskEditDto modify(User user, SchedulerTaskEditDto editDTO);

	void delete(User user, Long schedulerTaskId);
}
