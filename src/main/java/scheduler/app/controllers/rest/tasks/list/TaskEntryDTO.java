package scheduler.app.controllers.rest.tasks.list;

public class TaskEntryDTO {

	private long taskId;

	private String taskName;
	private String description;
	private String taskParametersJSON;
	private String remoteURL;
	private String remoteParametersJSON;

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId( final long taskId ) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName( final String taskName ) {
		this.taskName = taskName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription( final String description ) {
		this.description = description;
	}

	public String getTaskParametersJSON() {
		return taskParametersJSON;
	}

	public void setTaskParametersJSON( final String taskParametersJSON ) {
		this.taskParametersJSON = taskParametersJSON;
	}

	public String getRemoteURL() {
		return remoteURL;
	}

	public void setRemoteURL( final String remoteURL ) {
		this.remoteURL = remoteURL;
	}

	public String getRemoteParametersJSON() {
		return remoteParametersJSON;
	}

	public void setRemoteParametersJSON( final String remoteParametersJSON ) {
		this.remoteParametersJSON = remoteParametersJSON;
	}
}
