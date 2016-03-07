package scheduler.app.controllers.rest.users.edit.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import scheduler.app.dto.Dto;

@Getter
@Setter
public class NewUserDto implements Dto {

    private Long userId;

    @NotBlank(message = "errors.user_login_must_not_be_empty")
    private String login;

    @NotBlank(message = "errors.user_name_must_not_be_empty")
    private String name;

    @NotBlank(message = "errors.user_password_must_not_be_empty")
    private String password;

    @NotBlank(message = "errors.user_password_confirm_must_not_be_empty")
    private String passwordConfirm;
}
