package scheduler.app.controllers.rest.scheduler.tasks.item.edit.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import scheduler.app.dto.Dto;
import scheduler.app.models.SchedulerTaskType;

@Getter
@Setter
@EqualsAndHashCode
public class SchedulerTaskEditDto implements Dto {
	private Long taskId;
	private SchedulerTaskType taskType;
	private String taskName;
	private String taskDescription;
	private String taskParametersJSON;
	private RemoteJobEditDto remoteJob;

	@Override
	public String toString() {
		return String.format("#%d: %s (%s)", taskId, taskName, taskType);
	}
}
