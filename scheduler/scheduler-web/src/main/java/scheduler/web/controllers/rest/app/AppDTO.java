package scheduler.web.controllers.rest.app;

import lombok.Getter;
import lombok.Setter;
import scheduler.web.dto.UserDto;

@Getter
@Setter
public class AppDTO {

	private final String projectName;
	private UserDto currentUser;

	public AppDTO(final String projectName) {
		this.projectName = projectName;
	}

	@Override
	public String toString() {
		return String.format( "%s: %s", projectName, currentUser );
	}
}
