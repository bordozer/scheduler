package scheduler.app.controllers.rest.users.edit;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
public class UserEditDto {

    @NotBlank(message = "errors.user_login_must_not_be_empty")
    private String login;

    @NotBlank(message = "errors.user_name_must_not_be_empty")
    private String name;

    @NotBlank(message = "errors.user_password_must_not_be_empty")
    private String password;
}
