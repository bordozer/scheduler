package scheduler.app.dto;

import org.apache.commons.lang.StringEscapeUtils;
import scheduler.app.models.User;

public class UserDTO {

	private long userId;
	private String userName;

	public UserDTO( final User user ) {
		this.userId = user.getId();
		this.userName = StringEscapeUtils.escapeJavaScript( user.getUsername() );
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId( final long userId ) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName( final String userName ) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return String.format( "#%d: %s", userId, userName );
	}
}
