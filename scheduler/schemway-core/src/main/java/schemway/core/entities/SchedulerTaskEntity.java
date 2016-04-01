package schemway.core.entities;

import lombok.Getter;
import lombok.Setter;
import schemway.core.models.SchedulerTaskType;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "T_SCH_TASK")
//@org.hibernate.annotations.Cache(region = "common", usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
public class SchedulerTaskEntity implements DBEntity {

	@Id
	@Column(name = "C_SCH_TASK_ID", unique = true)
	@GeneratedValue(generator = "T_SCH_TASK_GEN")
	@SequenceGenerator(name = "T_SCH_TASK_GEN", sequenceName = "T_SCH_TASK_SEQ", allocationSize = 20)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "C_USER_ID", nullable = false)
	private schemway.core.entities.UserEntity user;

	@Enumerated(EnumType.STRING)
	@Column(name = "C_SCH_TASK_TYPE", columnDefinition = "VARCHAR(10)")
	private SchedulerTaskType taskType;

	@Column(name = "C_SCH_TASK_NAME", columnDefinition = "VARCHAR(255)")
	private String taskName;

	@Column(name = "C_SCH_TASK_DESCR", columnDefinition = "CLOB")
	private String taskDescription;

    @Column(name = "C_SCH_TASK_PARAM_JSON", columnDefinition = "CLOB")
    private String taskParametersJSON;

	@OneToOne(mappedBy = "schedulerTask", cascade = CascadeType.ALL)
	private schemway.core.entities.RemoteJobEntity remoteJob;
}
