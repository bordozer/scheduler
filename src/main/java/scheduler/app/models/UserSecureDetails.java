package scheduler.app.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class UserSecureDetails implements Model {

	private Long id;
	private Long userId;
	private String login;
	private String authString;
	private UserRole role;
}
