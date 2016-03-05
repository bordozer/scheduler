package scheduler.app.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMethod;

@Getter
@Setter
@EqualsAndHashCode
public class RemoteJobDTO implements DTO {
	private Long id;
	private String url;
	private RequestMethod requestMethod;
	private String postJson;
}
