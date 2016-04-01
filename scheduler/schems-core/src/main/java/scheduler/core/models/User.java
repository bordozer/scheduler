package scheduler.core.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class User implements Model {
	private Long id;
	private String username;

	@Override
	public String toString() {
		return String.format("#%d: %s", id, username);
	}
}
