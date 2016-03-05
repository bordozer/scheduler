package scheduler.app.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class UserSecureDetails implements Model {
	private Long id;
	private User user;
	private String login;
	private UserRole role;
	private String passwordEncrypted;

	@Override
	public String toString() {
		return String.format("#%d, user: %s", id, user);
	}
}
