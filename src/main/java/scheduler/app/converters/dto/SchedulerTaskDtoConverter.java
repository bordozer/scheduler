package scheduler.app.converters.dto;

import scheduler.app.controllers.rest.tasks.item.edit.SchedulerTaskEditDTO;
import scheduler.app.dto.SchedulerTaskDTO;
import scheduler.app.models.SchedulerTask;
import scheduler.app.models.User;

public interface SchedulerTaskDtoConverter extends GenericDtoConverter<SchedulerTask, SchedulerTaskDTO>{

    SchedulerTaskEditDTO toEditDto(SchedulerTask task);

    SchedulerTask toModel(final User user, SchedulerTaskEditDTO editDTO);
}
