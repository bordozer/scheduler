package scheduler.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	private Long userId;
	private String userName;

	@Override
	public String toString() {
		return String.format("#%d: %s", userId, userName);
	}
}
