package scheduler.app.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import scheduler.app.models.UserRole;

import javax.persistence.*;

@Entity
@Table(name = "T_USER_SECURITY")
@org.hibernate.annotations.Cache(region = "common", usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
@EqualsAndHashCode
public class UserSecureDetailsEntry implements DBEntity {

	@Id
	@Column(name = "T_USER_SECURITY_ID", unique = true)
	@GeneratedValue(generator = "T_USER_SECURITY_GEN")
	@SequenceGenerator(name = "T_USER_SECURITY_GEN", sequenceName = "T_USER_SECURITY_ID_SEQ", allocationSize = 20)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "C_USER_ID", nullable = false)
	private UserEntry user;

	@Column(name = "C_USER_LOGIN", unique = true, columnDefinition = "VARCHAR(16)")
	private String login;

	@Column(name = "C_USER_PASSWORD", unique = true, columnDefinition = "VARCHAR(255)")
	private String password;

	@Column(name = "C_USER_AUTH_STRING", columnDefinition = "VARCHAR(16)")
	private String authString;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "C_USER_ROLE")
	private UserRole role;
}
