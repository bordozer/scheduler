package scheduler.app.controllers.rest.tasks.item.edit;

public class TaskEntryEditDTO {

	private long taskId;
	private String taskName;

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId( final long taskId ) {
		this.taskId = taskId;
	}

	public void setTaskName( final String taskName ) {
		this.taskName = taskName;
	}

	public String getTaskName() {
		return taskName;
	}
}
