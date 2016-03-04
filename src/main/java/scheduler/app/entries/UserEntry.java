package scheduler.app.entries;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@org.hibernate.annotations.Cache(region = "common", usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "T_USER")
@Getter
@Setter
public class UserEntry {

    @Id
    @Column(name = "C_USER_ID", unique = true)
    @GeneratedValue
    private Long id;

    @Column(name = "C_USER_LOGIN", unique = true, columnDefinition = "VARCHAR(16)")
    private String login;

    @Column(name = "C_USER_NAME", unique = true, columnDefinition = "VARCHAR(100)")
    private String username;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserSecureDetailsEntry secureDetails;

    @Override
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
    }
}
