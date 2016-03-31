package converters.dto;

import com.beust.jcommander.internal.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import scheduler.core.models.User;
import scheduler.web.converters.dto.UserDtoConverter;
import scheduler.web.dto.UserDto;
import utils.TestData;
import utils.TestDataDto;
import utils.TestDataModels;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class UserDtoConverterTest {

    @InjectMocks
    private UserDtoConverter sut = new UserDtoConverter();

    @Test
    public void shouldConvertToModel() {
        User model = sut.toModel(TestDataModels.currentUser(), TestDataDto.user());

        Assert.assertEquals(TestData.USER_ID, model.getId());
        assertEquals(TestData.USER_NAME, model.getUsername());
    }

    @Test
    public void shouldConvertToDto() {
        UserDto dto = sut.toDto(TestDataModels.user());
        checkDto(dto);
    }

    @Test
    public void shouldConvertToDtos() {
        List<UserDto> models = sut.toDtos(Lists.newArrayList(TestDataModels.user()));

        assertNotNull(models);
        assertTrue(models.size() == 1);

        UserDto dto = models.get(0);
        checkDto(dto);
    }

    private void checkDto(final UserDto dto) {
        assertEquals(TestData.USER_ID, dto.getUserId());
        assertEquals(TestData.USER_NAME, dto.getUserName());
    }
}