package scheduler.web.controllers.rest.scheduler.tasks.item.edit.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import scheduler.core.models.SchedulerTaskType;
import scheduler.web.dto.Dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode
public class SchedulerTaskEditDto implements Dto {
	private Long taskId;

	@NotNull(message = "errors.request_url_must_not_be_empty")
	private SchedulerTaskType taskType;

	@NotBlank(message = "errors.request_url_must_not_be_empty")
	private String taskName;

	private String taskDescription;

	@NotBlank(message = "errors.request_url_must_not_be_empty")
	private String taskParametersJSON;

	@Valid
	@NotNull(message = "errors.remote_job_must_not_be_null")
	private RemoteJobEditDto remoteJob;

	@Override
	public String toString() {
		return String.format("#%d: %s (%s)", taskId, taskName, taskType);
	}
}
