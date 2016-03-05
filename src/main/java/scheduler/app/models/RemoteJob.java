package scheduler.app.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMethod;

@Getter
@Setter
@EqualsAndHashCode
public class RemoteJob implements Model {
	private Long id;
	private String url;
	private RequestMethod requestMethod;
	private String postJson;

	@Override
	public String toString() {
		return String.format("#%d: %s (%s)", id, url, requestMethod);
	}
}
