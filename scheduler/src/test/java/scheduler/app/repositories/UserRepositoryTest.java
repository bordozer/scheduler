package scheduler.app.repositories;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Test;
import org.springframework.test.annotation.Commit;
import scheduler.app.entities.UserEntity;
import scheduler.app.entities.UserSecureDetailsEntity;
import scheduler.app.models.UserRole;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@DatabaseSetup(value = UserRepositoryTest.TEST_DATA_SET, type = DatabaseOperation.INSERT)
@DatabaseTearDown(value = UserRepositoryTest.TEST_DATA_SET, type = DatabaseOperation.DELETE)
public class UserRepositoryTest extends AbstractRepositoryTest {

    public static final String TEST_DATA_SET = "classpath:/users/users.xml";

    @Inject
    private UserRepository sut;

    @Test
    @Commit
    public void shouldFindUser() {
        UserEntity userEntity1 = selectAndCheckUser(USER_CURRY, UserRole.ADMIN);
        UserSecureDetailsEntity secureDetails1 = userEntity1.getSecureDetails();
        assertThat(secureDetails1.getLogin(), is("curry"));
        assertThat(secureDetails1.getPassword(), is("$2a$10$oNXFlhGI0LekTBIedkILTeBYcDCT11Mb4NmIO.p5WH.6isYLLs9uC"));
    }

    @Test
    @Commit
    public void shouldFindUser2() {
        UserEntity userEntity2 = selectAndCheckUser(USER_IBAKA, UserRole.USER);
        UserSecureDetailsEntity secureDetails2 = userEntity2.getSecureDetails();
        assertThat(secureDetails2.getLogin(), is("ibaka"));
        assertThat(secureDetails2.getPassword(), is("$2a$10$Km5QAjX0JsCE0DSpPNroLucN1/wlfc4V4PCDJRbb0/yH0jnso0dcC"));
    }

    @Test
    @Commit
    public void shouldFindUser3() {
        UserEntity userEntity3 = selectAndCheckUser(USER_DURANT, UserRole.USER);
        UserSecureDetailsEntity secureDetails3 = userEntity3.getSecureDetails();
        assertThat(secureDetails3.getLogin(), is("durant"));
        assertThat(secureDetails3.getPassword(), is("$2a$10$Km5QAjX0JsCE0DSpPNroLucN1/wlfc4V4PCDJRbb0/yH0jnso0dcC"));
    }

    @Test
    @Commit
    public void shouldCreateNewUserWithoutUserSecureDetails() {
        String userName = "Newly created user";

        UserEntity constructedUser = new UserEntity();
        constructedUser.setUsername(userName);

        UserEntity savedUser = sut.saveAndFlush(constructedUser);
        assertThat(constructedUser.getId(), is(notNullValue()));
        assertThat(savedUser.getId(), is(constructedUser.getId()));
        assertThat(savedUser, is(constructedUser));

        UserEntity loadedUser = sut.findById(savedUser.getId());
        assertThat(constructedUser.getId(), is(loadedUser.getId()));
        assertThat(loadedUser.getUsername(), is(userName));

        UserSecureDetailsEntity secureDetails = loadedUser.getSecureDetails();
        assertThat(secureDetails, is(nullValue()));
    }

    @Test(expected = PersistenceException.class)
    @Commit
    public void shouldThrowExceptionIfSecureDetailsHasNotUser() {

        String userName = "Newly created user";

        UserSecureDetailsEntity constructedSecureDetails = new UserSecureDetailsEntity();
        constructedSecureDetails.setLogin("new_user_login");
        constructedSecureDetails.setPassword("$2a$10$Km5QAjX0JsCE0DSpPNroLucN1/wlfc4V4PCDJRbb0/yH0jnso0dcC");
        constructedSecureDetails.setRole(UserRole.USER);

        UserEntity constructedUser = new UserEntity();
        constructedUser.setUsername(userName);
        constructedUser.setSecureDetails(constructedSecureDetails);

        UserEntity savedUser = sut.saveAndFlush(constructedUser);
    }

    @Test
    @Commit
    public void shouldCreateNewUserWithUserSecureDetails() {

        String userName = "Newly created user";
        String userLogin = "new_user_login";
        String password = "$2a$10$Km5QAjX0JsCE0DSpPNroLucN1/wlfc4V4PCDJRbb0/yH0jnso0dcC";
        UserRole userRole = UserRole.USER;

        UserSecureDetailsEntity constructedSecureDetails = new UserSecureDetailsEntity();
        constructedSecureDetails.setLogin(userLogin);
        constructedSecureDetails.setPassword(password);
        constructedSecureDetails.setRole(userRole);

        UserEntity constructedUser = new UserEntity();
        constructedUser.setUsername(userName);
        constructedUser.setSecureDetails(constructedSecureDetails);

        constructedSecureDetails.setUser(constructedUser);

        UserEntity savedUser = sut.saveAndFlush(constructedUser);
        assertThat(constructedUser.getId(), is(notNullValue()));
        assertThat(savedUser.getId(), is(constructedUser.getId()));
        assertThat(savedUser, is(constructedUser));

        UserEntity loadedUser = sut.findById(savedUser.getId());
        assertThat(constructedUser.getId(), is(loadedUser.getId()));
        assertThat(loadedUser.getUsername(), is(userName));

        UserSecureDetailsEntity secureDetails = loadedUser.getSecureDetails();
        assertThat(secureDetails, is(notNullValue()));
        assertThat(secureDetails.getId(), is(notNullValue()));
        assertThat(secureDetails.getLogin(), is(userLogin));
        assertThat(secureDetails.getPassword(), is(password));
        assertThat(secureDetails.getRole(), is(userRole));
        assertThat(secureDetails.getUser(), not(is(constructedUser)));
        assertThat(secureDetails.getUser(), not(is(savedUser)));
        assertThat(secureDetails.getUser(), is(loadedUser));
    }

    private UserEntity selectAndCheckUser(final TestUser testUser, final UserRole userRole) {
        UserEntity userEntity = sut.findById(testUser.getId());
        assertThat(userEntity, is(notNullValue()));
        assertThat(userEntity.getId(), is(testUser.getId()));
        assertThat(userEntity.getUsername(), is(testUser.getUserName()));

        UserSecureDetailsEntity secureDetails = userEntity.getSecureDetails();
        assertThat(secureDetails, is(notNullValue()));
        assertThat(secureDetails.getUser(), is(notNullValue()));
        assertThat(secureDetails.getUser().getId(), is(testUser.getId()));
        assertThat(secureDetails.getRole(), is(userRole));

        return userEntity;
    }
}