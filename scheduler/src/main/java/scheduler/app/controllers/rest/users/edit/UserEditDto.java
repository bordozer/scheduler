package scheduler.app.controllers.rest.users.edit;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEditDto {
    private String login;
    private String name;
    private String password;
}
