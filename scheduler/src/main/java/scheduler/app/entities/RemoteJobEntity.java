package scheduler.app.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import scheduler.app.models.RequestMethod;

import javax.persistence.*;

@Entity
@Table(name = "T_REMOTE_JOB")
@org.hibernate.annotations.Cache(region = "common", usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
public class RemoteJobEntity implements DBEntity {

    @Id
	@Column(name = "C_REMOTE_JOB_ID", unique = true)
	@GeneratedValue(generator = "T_REMOTE_JOB_GEN")
	@SequenceGenerator(name = "T_REMOTE_JOB_GEN", sequenceName = "T_REMOTE_JOB_SEQ", allocationSize = 20)
	private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "C_SCH_TASK_ID", nullable = false)
	private SchedulerTaskEntity schedulerTask;

	@Column(name = "C_REQUEST_URL", columnDefinition = "CLOB")
	private String requestUrl;

	@Enumerated(EnumType.STRING)
	@Column(name = "C_REQUEST_METHOD", columnDefinition = "VARCHAR(10)")
	private RequestMethod requestMethod;

	@Column(name = "C_USER_AUTH_STRING", columnDefinition = "VARCHAR(255)")
	private String authString;

    @Column(name = "C_POST_JSON", columnDefinition = "CLOB")
    private String postJson;
}
