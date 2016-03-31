package scheduler.app.controllers.rest.users.edit.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserEditDto {

    @NotNull
    private Long userId;

    @Size(min = 5, max = 100, message = "errors.name_too_long")
    @NotBlank(message = "errors.user_name_must_not_be_empty")
    private String name;
}
