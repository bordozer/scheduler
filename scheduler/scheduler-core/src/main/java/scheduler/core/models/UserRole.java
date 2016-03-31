package scheduler.core.models;

import lombok.Getter;

@Getter
public enum UserRole {
	ADMIN("ROLE_ADMIN")
	, USER("ROLE_USER")
	;

	private final String role;

	UserRole(final String role) {
		this.role = role;
	}
}
