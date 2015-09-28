package scheduler.app.models;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@org.hibernate.annotations.Cache( region = "common", usage = CacheConcurrencyStrategy.READ_WRITE )
@Table( name = "tasks" )
public class TaskEntry extends AbstractEntity {

	@Column( columnDefinition = "VARCHAR(255)" )
	private String taskName;

	@Column( columnDefinition = "TEXT" )
	private String description;

	@Column( columnDefinition = "TEXT" )
	private String taskParametersJSON;

	@Column( columnDefinition = "TEXT" )
	private String remoteURL;

	@Column( columnDefinition = "TEXT" )
	private String remoteParametersJSON;

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
