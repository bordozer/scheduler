package scheduler.app.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import scheduler.app.models.SchedulerTaskType;

import javax.persistence.*;

@Entity
@Table(name = "T_SCH_TASK")
@org.hibernate.annotations.Cache(region = "common", usage = CacheConcurrencyStrategy.READ_WRITE)
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

	@Enumerated(EnumType.STRING)
	@Column(name = "C_SCH_TASK_TYPE", columnDefinition = "VARCHAR(10)")
	private SchedulerTaskType schedulerTaskType;

	@Column(name = "C_SCH_TASK_NAME", columnDefinition = "VARCHAR(255)")
	private String taskName;

	@Column(name = "C_SCH_TASK_DESCR", columnDefinition = "CLOB")
	private String taskDescription;

    @Column(name = "C_SCH_TASK_PARAM_JSON", columnDefinition = "CLOB")
    private String taskParametersJSON;

	@OneToOne(mappedBy = "schedulerTask", cascade = CascadeType.ALL)
	private RemoteJobEntity remoteJob;
}
