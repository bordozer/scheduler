package scheduler.app.dto;

import lombok.Getter;
import lombok.Setter;
import scheduler.app.models.SchedulerTaskType;

@Getter
@Setter
public class SchedulerTaskDto implements Dto {
	private Long taskId;
	private UserDto user;
	private SchedulerTaskType taskType;
	private String taskName;
	private String taskDescription;
	private String taskParametersJSON;
	private RemoteJobDto remoteJob;

	@Override
	public String toString() {
		return String.format("#%d: %s (%s)", taskId, taskName, taskType);
	}
}
