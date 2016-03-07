package scheduler.app.converters.dto;

import com.beust.jcommander.internal.Lists;
import org.junit.Test;
import org.mockito.InjectMocks;
import scheduler.app.dto.Dto;
import scheduler.app.dto.UserDto;
import scheduler.app.models.Model;
import scheduler.app.models.User;
import scheduler.app.utils.TestDataModels;

import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AbstractGenericDtoConverterTest {

    @InjectMocks
    private AbstractGenericDtoConverter sut = new AbstractGenericDtoConverter() {
        @Override
        protected Function taskMapper() {
            return o -> null;
        }

        @Override
        protected Model doConvertToModel(final User user, final Dto dto) {
            return null;
        }
    };

    @Test(expected = IllegalArgumentException.class)
    @SuppressWarnings("unchecked")
    public void shouldThrowExceptionIfCurrentUserIsNull() {
        sut.toModel(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    @SuppressWarnings("unchecked")
    public void shouldThrowExceptionIfDtoIsNull() {
        sut.toModel(TestDataModels.currentUser(), null);
    }

    @Test(expected = IllegalArgumentException.class)
    @SuppressWarnings("unchecked")
    public void shouldThrowExceptionIfModelIsNull() {
        sut.toDto(null);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void shouldConvertToDtosIfNullIsPassed() {
        List<UserDto> dtos = sut.toDtos(null);

        assertNotNull(dtos);
        assertTrue(dtos.size() == 0);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void shouldConvertToDtosIfEmptyListIsPassed() {
        List<UserDto> dtos = sut.toDtos(Lists.newArrayList());

        assertNotNull(dtos);
        assertTrue(dtos.size() == 0);
    }
}