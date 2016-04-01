package schemway.web.dto;

import lombok.Getter;
import lombok.Setter;
import schemway.core.models.RequestMethod;

@Getter
@Setter
public class RemoteJobDto implements Dto {
	private Long remoteJobId;
	private String requestUrl;
	private RequestMethod requestMethod;
	private String authString;
	private String postJson;

	@Override
	public String toString() {
		return String.format("#%d: %s (%s)", remoteJobId, requestUrl, requestMethod);
	}
}
