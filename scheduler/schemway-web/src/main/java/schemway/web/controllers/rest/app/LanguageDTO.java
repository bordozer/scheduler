package schemway.web.controllers.rest.app;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LanguageDTO {
	private final String name;
	private final String country;

	@Override
	public String toString() {
		return String.format( "%s: %s", name, country );
	}
}
