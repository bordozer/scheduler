package scheduler.app.controllers.rest.tasks.item.edit;

import lombok.Getter;
import lombok.Setter;
import scheduler.app.dto.DTO;

@Getter
@Setter
public class SchedulerTaskEditDTO implements DTO{
	private Long taskId;
	private String taskName;
	private String taskDescription;
}
