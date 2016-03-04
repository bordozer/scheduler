package scheduler.app.entries;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import scheduler.app.models.UserRole;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@org.hibernate.annotations.Cache(region = "common", usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "T_USER_SECURITY")
@Getter
@Setter
@EqualsAndHashCode
public class UserSecureDetailsEntry implements DBEntity {

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "C_USER_ID", nullable = false)
    private UserEntry user;

    @Column(name = "C_USER_LOGIN", unique = true, columnDefinition = "VARCHAR(16)")
    private String login;

    @Column(name = "C_USER_PASSWORD", unique = true, columnDefinition = "VARCHAR(16)")
    private String password;

    @Column(name = "C_USER_AUTH_STRING", columnDefinition = "VARCHAR(16)")
    private String authString;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "C_USER_LOGIN")
    private UserRole role;
}
