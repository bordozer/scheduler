package scheduler.app.entries;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "T_USER")
@org.hibernate.annotations.Cache(region = "common", usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
@EqualsAndHashCode
public class UserEntry implements DBEntity {

	@Id
	@Column(name = "C_USER_ID", unique = true)
	@GeneratedValue(generator = "T_USER_GEN")
	@SequenceGenerator(name = "T_USER_GEN", sequenceName = "T_USER_SEQ", allocationSize = 20)
	private Long id;

	@Column(name = "C_USER_NAME", unique = true, columnDefinition = "VARCHAR(100)")
	private String username;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private UserSecureDetailsEntry secureDetails;

    /*@Override
	public int hashCode() {
        return (int) (31 * id);
    }

    @Override
    public boolean equals(final Object obj) {

        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (!(obj instanceof UserEntry)) {
            return false;
        }

        final UserEntry userEntry = (UserEntry) obj;
        return userEntry.getId().equals((id));
    }

    @Override
    public String toString() {
        return String.format("#%d: %s ( %s )", getId(), login, username);
    }*/
}
