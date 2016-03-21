package scheduler.app.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "T_USER")
//@org.hibernate.annotations.Cache(region = "common", usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
public class UserEntity implements DBEntity {

	@Id
	@Column(name = "C_USER_ID", unique = true)
	@GeneratedValue(generator = "T_USER_GEN")
	@SequenceGenerator(name = "T_USER_GEN", sequenceName = "T_USER_SEQ", allocationSize = 20)
	private Long id;

	@Column(name = "C_USER_NAME", unique = true, columnDefinition = "VARCHAR(100)")
	private String username;

	@OneToOne(mappedBy = "user", optional = false)
	private UserSecureDetailsEntity secureDetails;

	@Override
	public String toString() {
		return String.format("#%d: %s", id, username);
	}
}
