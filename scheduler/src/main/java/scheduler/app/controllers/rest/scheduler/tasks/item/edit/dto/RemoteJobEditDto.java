package scheduler.app.controllers.rest.scheduler.tasks.item.edit.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMethod;
import scheduler.app.dto.Dto;

@Getter
@Setter
@EqualsAndHashCode
public class RemoteJobEditDto implements Dto {
	private Long id;
	private String requestUrl;
	private RequestMethod requestMethod;
	private String authString;
	private String postJson;

	@Override
	public String toString() {
		return String.format("#%d: %s (%s)", id, requestUrl, requestMethod);
	}
}