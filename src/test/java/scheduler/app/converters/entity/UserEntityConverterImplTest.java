package scheduler.app.converters.entity;

import org.junit.Test;
import org.mockito.InjectMocks;
import scheduler.app.entities.UserEntity;
import scheduler.app.models.User;
import scheduler.app.utils.TestData;
import scheduler.app.utils.TestDataEntities;
import scheduler.app.utils.TestDataModels;

import static org.junit.Assert.assertEquals;

public class UserEntityConverterImplTest {

    @InjectMocks
    private UserEntityConverterImpl sut = new UserEntityConverterImpl();

    @Test
    public void shouldPopulateEntity() {
        final UserEntity entity = new UserEntity();
        sut.populateEntity(entity, TestDataModels.user());

        assertEquals(TestData.USER_ID, entity.getId());
        assertEquals(TestData.USER_NAME, entity.getUsername());
    }

    @Test
    public void shouldConvertEntityToModel() {
        final User model = sut.toModel(TestDataEntities.user());

        assertEquals(TestData.USER_ID, model.getId());
        assertEquals(TestData.USER_NAME, model.getUsername());
    }
}