package scheduler.app.controllers.rest.tasks.item.edit;

import lombok.Getter;
import lombok.Setter;
import scheduler.app.dto.Dto;

@Getter
@Setter
public class SchedulerTaskEditDto implements Dto {
	private Long taskId;
	private String taskName;
	private String taskDescription;
}
