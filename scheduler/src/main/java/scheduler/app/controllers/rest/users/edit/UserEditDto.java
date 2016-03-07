package scheduler.app.controllers.rest.users.edit;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
public class UserEditDto {

    @NotBlank
    private String login;

    @NotBlank
    private String name;

    @NotBlank
    private String password;
}
