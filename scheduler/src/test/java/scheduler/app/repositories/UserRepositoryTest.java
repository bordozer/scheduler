package scheduler.app.repositories;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import scheduler.app.entities.UserEntity;
import scheduler.app.entities.UserSecureDetailsEntity;
import scheduler.app.models.UserRole;
import scheduler.config.HibernateTestConfig;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AbstractRepositoryTest.Config.class, HibernateTestConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
//        DirtiesContextTestExecutionListener.class,
//        TransactionalTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class
})
@DatabaseSetup(value = UserRepositoryTest.TEST_DATA_SET, type = DatabaseOperation.INSERT)
@DatabaseTearDown(value = UserRepositoryTest.TEST_DATA_SET, type = DatabaseOperation.DELETE)
@DbUnitConfiguration(databaseConnection = "h2TestConnection")
public class UserRepositoryTest extends AbstractRepositoryTest {

    public static final String TEST_DATA_SET = "classpath:/users/users.xml";

    @Inject
    private UserRepository sut;

    @Test
    public void shouldFindUser() {
        UserEntity userEntity1 = selectAndCheckUser(USER_CURRY, UserRole.ADMIN);
        UserSecureDetailsEntity secureDetails1 = userEntity1.getSecureDetails();
        assertThat(secureDetails1.getLogin(), is("curry"));
        assertThat(secureDetails1.getPassword(), is("$2a$10$oNXFlhGI0LekTBIedkILTeBYcDCT11Mb4NmIO.p5WH.6isYLLs9uC"));

        UserEntity userEntity2 = selectAndCheckUser(USER_IBAKA, UserRole.USER);
        UserSecureDetailsEntity secureDetails2 = userEntity2.getSecureDetails();
        assertThat(secureDetails2.getLogin(), is("ibaka"));
        assertThat(secureDetails2.getPassword(), is("$2a$10$Km5QAjX0JsCE0DSpPNroLucN1/wlfc4V4PCDJRbb0/yH0jnso0dcC"));

        UserEntity userEntity3 = selectAndCheckUser(USER_DURANT, UserRole.USER);
        UserSecureDetailsEntity secureDetails3 = userEntity3.getSecureDetails();
        assertThat(secureDetails3.getLogin(), is("durant"));
        assertThat(secureDetails3.getPassword(), is("$2a$10$Km5QAjX0JsCE0DSpPNroLucN1/wlfc4V4PCDJRbb0/yH0jnso0dcC"));
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