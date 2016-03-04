package scheduler.app.entries;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@org.hibernate.annotations.Cache(region = "common", usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "tasks")
@Getter
@Setter
public class TaskEntry {

    @Id
    @Column(name = "C_TASK_ID", unique = true)
    @GeneratedValue
    private Long id;

    @Column(name = "", columnDefinition = "VARCHAR(255)")
    private String taskName;

    @Column(name = "", columnDefinition = "TEXT")
    private String description;

    @Column(name = "", columnDefinition = "TEXT")
    private String taskParametersJSON;

    @Column(name = "", columnDefinition = "TEXT")
    private String remoteURL;

    @Column(name = "", columnDefinition = "TEXT")
    private String remoteParametersJSON;
}
