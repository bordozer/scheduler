package schemway.web.controllers.rest.scheduler.tasks.item.edit.services;

import schemway.core.models.User;
import schemway.web.controllers.rest.scheduler.tasks.item.edit.dto.SchedulerTaskEditDto;

public interface SchedulerTaskEditService {

	SchedulerTaskEditDto create(User user, SchedulerTaskEditDto editDTO);

	SchedulerTaskEditDto modify(User user, SchedulerTaskEditDto editDTO);

	void delete(User user, Long schedulerTaskId);
}
