package scheduler.web.controllers.rest.users.edit.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import scheduler.web.dto.Dto;

import javax.validation.constraints.Size;

@Getter
@Setter
public class NewUserDto implements Dto {

    private Long userId;

    @Size(min = 5, max = 16, message = "errors.login_too_long")
    @NotBlank(message = "errors.user_login_must_not_be_empty")
    private String login;

    @Size(min = 5, max = 100, message = "errors.name_too_long")
    @NotBlank(message = "errors.user_name_must_not_be_empty")
    private String name;

    @Size(min = 8, max = 16, message = "errors.password_too_long")
    @NotBlank(message = "errors.user_password_must_not_be_empty")
    private String password;

    @Size(min = 8, max = 16, message = "errors.password_confirmation_too_long")
    @NotBlank(message = "errors.user_password_confirm_must_not_be_empty")
    private String passwordConfirm;
}
