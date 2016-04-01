package schemway.app.services.users;

import org.junit.Test;
import schemway.app.AbstractIntegrationTest;
import schemway.core.models.User;
import schemway.core.models.UserRole;
import schemway.core.models.UserSecureDetails;
import schemway.core.services.users.UserService;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;


public class UserServiceImplIntegrationTest extends AbstractIntegrationTest {

    @Inject
    private UserService sut;

    @Test
    public void shouldCreateUserAndReturnModel() {
        String username = "User Name 1";
        String login = "login1";

        User user = constructUser(username);
        UserSecureDetails details = constructUserSecureDetails(user, login);

        User createdUser = sut.create(user, details);
        assertThat(createdUser, is(notNullValue()));
        assertThat(createdUser.getUsername(), is(username));
    }

    @Test
    public void shouldCreateUserDetailsWhenCreateNewUser() {
        String username = "User Name 2";
        String login = "login2";

        User user = constructUser(username);
        UserSecureDetails details = constructUserSecureDetails(user, login);

        User createdUser = sut.create(user, details);

        UserSecureDetails secureDetails = sut.loadUserSecureDetails(createdUser.getId());
        assertThat(secureDetails, is(notNullValue()));
        assertThat(secureDetails.getLogin(), is(login));
        assertThat(secureDetails.getRole(), is(UserRole.USER));
        assertThat(secureDetails.getUser(), is(createdUser));
    }

    @Test
    public void shouldUpdateNewlyCreatedUser() {
        String username = "User Name 3";
        String login = "login1";

        User user = constructUser(username);
        UserSecureDetails details =  constructUserSecureDetails(user, login);

        User createdUser = sut.create(user, details);

        User loadedUser = sut.findByLogin(login);
        loadedUser.setUsername("Updated user name");

        User modifiedUser = sut.modify(loadedUser);

        assertThat(modifiedUser, is(notNullValue()));
        assertThat(modifiedUser, is(createdUser));
        assertThat(modifiedUser.getId(), is(createdUser.getId()));
        assertThat(modifiedUser.getUsername(), is("Updated user name"));

        UserSecureDetails secureDetails = sut.loadUserSecureDetails(createdUser.getId());
        assertThat(secureDetails, is(notNullValue()));
        assertThat(secureDetails.getLogin(), is(login));
        assertThat(secureDetails.getRole(), is(UserRole.USER));
        assertThat(secureDetails.getUser(), is(createdUser));
    }

    private User constructUser(final String username) {
        User user = new User();
        user.setUsername(username);
        return user;
    }

    private UserSecureDetails constructUserSecureDetails(final User user, final String login) {
        UserSecureDetails details = new UserSecureDetails();
        details.setLogin(login);
        details.setPasswordEncrypted("userpss"); // TODO: should it be a separate model?
        details.setRole(UserRole.USER);
        details.setUser(user);
        return details;
    }
}