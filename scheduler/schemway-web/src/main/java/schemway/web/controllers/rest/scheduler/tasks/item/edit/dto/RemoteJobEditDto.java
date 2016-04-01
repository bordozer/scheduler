package schemway.web.controllers.rest.scheduler.tasks.item.edit.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import schemway.core.models.RequestMethod;
import schemway.web.dto.Dto;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode
public class RemoteJobEditDto implements Dto {

	private Long remoteJobId;

	@NotBlank(message = "errors.request_url_must_not_be_empty")
	private String requestUrl;

	@NotNull(message = "errors.request_method_must_not_be_null")
	private RequestMethod requestMethod;

	private String authString;

	private String postJson;

	@Override
	public String toString() {
		return String.format("#%d: %s (%s)", remoteJobId, requestUrl, requestMethod);
	}
}
