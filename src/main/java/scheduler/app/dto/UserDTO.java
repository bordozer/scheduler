package scheduler.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO implements DTO {

	private Long userId;
	private String userName;

	@Override
	public String toString() {
		return String.format("#%d: %s", userId, userName);
	}
}
