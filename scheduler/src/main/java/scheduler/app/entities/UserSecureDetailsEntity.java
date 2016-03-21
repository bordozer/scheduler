package scheduler.app.entities;

import lombok.Getter;
import lombok.Setter;
import scheduler.app.models.UserRole;

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
@Table(name = "T_USER_SECURITY")
//@org.hibernate.annotations.Cache(region = "common", usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
public class UserSecureDetailsEntity implements DBEntity {

	@Id
	@Column(name = "T_USER_SECURITY_ID", unique = true)
	@GeneratedValue(generator = "T_USER_SECURITY_GEN")
	@SequenceGenerator(name = "T_USER_SECURITY_GEN", sequenceName = "T_USER_SECURITY_SEQ", allocationSize = 20)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "C_USER_ID", nullable = false)
	private UserEntity user;

	@Column(name = "C_USER_LOGIN", unique = true, columnDefinition = "VARCHAR(16)")
	private String login;

	@Column(name = "C_USER_PASSWORD", unique = true, columnDefinition = "VARCHAR(255)")
	private String password;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "C_USER_ROLE", columnDefinition = "VARCHAR(10)")
	private UserRole role;

	@Override
	public String toString() {
		return String.format("%s - security", user);
	}
}
