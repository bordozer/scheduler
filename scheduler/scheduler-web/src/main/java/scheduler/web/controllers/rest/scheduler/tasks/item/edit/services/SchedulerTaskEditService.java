package scheduler.web.controllers.rest.scheduler.tasks.item.edit.services;

import scheduler.core.models.User;
import scheduler.web.controllers.rest.scheduler.tasks.item.edit.dto.SchedulerTaskEditDto;

public interface SchedulerTaskEditService {

	SchedulerTaskEditDto create(User user, SchedulerTaskEditDto editDTO);

	SchedulerTaskEditDto modify(User user, SchedulerTaskEditDto editDTO);

	void delete(User user, Long schedulerTaskId);
}
