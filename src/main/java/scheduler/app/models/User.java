package scheduler.app.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import scheduler.app.entries.DBEntity;

@Getter
@Setter
@EqualsAndHashCode
public class User implements Model {
    private Long id;
    private String username;
}
