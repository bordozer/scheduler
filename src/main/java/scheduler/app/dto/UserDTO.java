package scheduler.app.dto;

import org.apache.commons.lang.StringEscapeUtils;
import scheduler.app.entries.UserEntry;

public class UserDTO {

    private long userId;
    private String userName;

    public UserDTO(final UserEntry userEntry) {
        this.userId = userEntry.getId();
        this.userName = StringEscapeUtils.escapeJavaScript(userEntry.getUsername());
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(final long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return String.format("#%d: %s", userId, userName);
    }
}
