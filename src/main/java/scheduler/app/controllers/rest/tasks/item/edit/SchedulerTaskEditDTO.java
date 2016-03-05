package scheduler.app.controllers.rest.tasks.item.edit;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchedulerTaskEditDTO {

	private Long taskId;
	private String taskName;
	private String taskDescription;
}
