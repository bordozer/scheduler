package scheduler.app.converters.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import scheduler.app.entities.UserEntity;
import scheduler.app.entities.UserSecureDetailsEntity;
import scheduler.app.models.User;
import scheduler.app.models.UserSecureDetails;
import scheduler.app.repositories.UserRepository;
import scheduler.app.utils.TestData;
import scheduler.app.utils.TestDataEntities;
import scheduler.app.utils.TestDataModels;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserSecureDetailsConverterImplTest {

    @InjectMocks
    private UserSecureDetailsConverterImpl sut = new UserSecureDetailsConverterImpl();

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserEntityConverter userEntityConverter;

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfEntityIsNullWhenPopulatesEntity() {
        sut.populateEntity(null, new UserSecureDetails());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfUserIsNullWhenPopulatesEntity() {
        sut.populateEntity(new UserSecureDetailsEntity(), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfUserIsNullWhenConvertsToModel() {
        sut.toModel(null);
    }

    @Test
    public void shouldPopulateEntity() {
        final UserEntity user = TestDataEntities.user();
        when(userRepository.findById(TestData.USER_ID)).thenReturn(user);

        UserSecureDetailsEntity entity = new UserSecureDetailsEntity();
        sut.populateEntity(entity, TestDataModels.userSecureDetails(TestDataModels.user()));

        verify(userRepository, times(1)).findById(TestData.USER_ID);

        assertEquals(TestData.USER_SECURE_DETAILS_ID, entity.getId());
        assertEquals(TestData.USER_LOGIN, entity.getLogin());
        assertEquals(TestData.USER_PASSWORD, entity.getPassword());
        assertEquals(TestData.USER_ROLE, entity.getRole());
        assertTrue(user == entity.getUser());
    }

    @Test
    public void shouldConvertEntityToModel() {
        final UserSecureDetailsEntity entity = TestDataEntities.userSecureDetails();
        final UserEntity userEntity = entity.getUser();
        User user = TestDataModels.user();
        when(userEntityConverter.toModel(userEntity)).thenReturn(user);

        final UserSecureDetails model = sut.toModel(entity);

        assertEquals(TestData.USER_SECURE_DETAILS_ID, model.getId());
        assertEquals(TestData.USER_LOGIN, model.getLogin());
        assertEquals(TestData.USER_PASSWORD, model.getPasswordEncrypted());
        assertEquals(TestData.USER_ROLE, model.getRole());
        assertTrue(user == model.getUser());
    }
}