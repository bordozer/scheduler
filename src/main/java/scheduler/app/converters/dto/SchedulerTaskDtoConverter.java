package scheduler.app.converters.dto;

import scheduler.app.controllers.rest.tasks.item.edit.SchedulerTaskEditDTO;
import scheduler.app.controllers.rest.tasks.list.SchedulerTaskDTO;
import scheduler.app.models.SchedulerTask;
import scheduler.app.models.User;

import java.util.List;

public interface SchedulerTaskDtoConverter {

    SchedulerTaskDTO toDto(SchedulerTask task);

    List<SchedulerTaskDTO> toDtos(List<SchedulerTask> taskEntries);

    SchedulerTaskEditDTO toEditDto(SchedulerTask task);

    SchedulerTask toModel(final User user, SchedulerTaskEditDTO editDTO);
}
