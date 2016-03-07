package scheduler.rest.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserData {
    private String login;
    private String name;
    private String password;
}
