package scheduler.app.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class IdDto {
	private final Long id;

	@Override
	public String toString() {
		return String.format("#%d", id);
	}
}
