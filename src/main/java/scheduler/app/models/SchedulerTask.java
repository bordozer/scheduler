package scheduler.app.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class SchedulerTask implements Model {
	private Long id;
	private User user;
	private SchedulerTaskType taskType;
	private String taskName;
	private String taskDescription;
	private String taskParametersJSON;
	private RemoteJob remoteJob;

	@Override
	public String toString() {
		return String.format("#%s, %s, %s, %s", id, user, taskName, taskType);
	}
}
