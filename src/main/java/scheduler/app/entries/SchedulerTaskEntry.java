package scheduler.app.entries;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@org.hibernate.annotations.Cache(region = "common", usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "T_SCH_TASK")
@Getter
@Setter
@EqualsAndHashCode
public class SchedulerTaskEntry implements DBEntity {

	@Id
	@Column(name = "C_SCH_TASK_ID", unique = true)
	@GeneratedValue(generator = "T_SCH_TASK_GEN")
	@SequenceGenerator(name = "T_SCH_TASK_GEN", sequenceName = "T_SCH_TASK_SEQ", allocationSize = 20)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "C_USER_ID", nullable = false)
	private UserEntry user;

	@Column(name = "C_SCH_TASK_NAME", columnDefinition = "VARCHAR(255)")
	private String taskName;

	@Column(name = "C_SCH_TASK_DESCR", columnDefinition = "VARCHAR(255)")
	private String taskDescription;

    /*Column(name = "C_SCH_TASK_DESCR", columnDefinition = "VARCHAR(255)")
	private String taskDescription;

    @Column(name = "C_SCH_TASK_PARAM_JSON", columnDefinition = "VARCHAR(255)")
    private String taskParametersJSON;

    @Column(name = "C_SCH_TASK_REMOTE_URL", columnDefinition = "TEXT")
    private String remoteURL;

    @Column(name = "C_SCH_TASK_REMOTE_PARAM_JSON", columnDefinition = "TEXT")
    private String remoteParametersJSON;*/
}
