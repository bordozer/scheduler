package scheduler.app.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "T_USER")
@org.hibernate.annotations.Cache(region = "common", usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
@EqualsAndHashCode
public class UserEntry implements DBEntity {

    public static final String NAMED_QUERY_FIND_BY_LOGIN = "UserEntry.NAMED_QUERY_FIND_BY_LOGIN";

	@Id
	@Column(name = "C_USER_ID", unique = true)
	@GeneratedValue(generator = "T_USER_GEN")
	@SequenceGenerator(name = "T_USER_GEN", sequenceName = "T_USER_SEQ", allocationSize = 20)
	private Long id;

	@Column(name = "C_USER_NAME", unique = true, columnDefinition = "VARCHAR(100)")
	private String username;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private UserSecureDetailsEntry secureDetails;

	@Override
	public String toString() {
		return String.format("#%d: %s", id, username);
	}
}
