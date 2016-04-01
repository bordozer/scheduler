package converters;

import com.beust.jcommander.internal.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import schemway.core.models.RemoteJob;
import schemway.core.models.SchedulerTask;
import schemway.core.models.User;
import schemway.web.converters.RemoteJobDtoConverter;
import schemway.web.converters.SchedulerTaskDtoConverter;
import schemway.web.converters.UserDtoConverter;
import schemway.web.dto.RemoteJobDto;
import schemway.web.dto.SchedulerTaskDto;
import schemway.web.dto.UserDto;
import utils.TestData;
import utils.TestDataDto;
import utils.TestDataModels;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SchedulerTaskDtoConverterTest {

    @InjectMocks
    private SchedulerTaskDtoConverter sut = new SchedulerTaskDtoConverter();

    @Mock
    private RemoteJobDtoConverter remoteJobDtoConverter;

    @Mock
    private UserDtoConverter userDtoConverter;

    @Test
    public void shouldConvertToModel() {
        User currentUser = TestDataModels.currentUser();
        SchedulerTaskDto dto = TestDataDto.schedulerTask();
        RemoteJob remoteJob = TestDataModels.remoteJob();

        when(remoteJobDtoConverter.toModel(currentUser, dto.getRemoteJob())).thenReturn(remoteJob);

        SchedulerTask model = sut.toModel(currentUser, dto);

        assertEquals(TestData.SCHEDULER_TASK_ID, model.getId());
        assertEquals(TestData.SCHEDULER_TASK_NAME, model.getTaskName());
        assertEquals(TestData.SCHEDULER_TASK_TYPE, model.getTaskType());
        assertEquals(TestData.SCHEDULER_TASK_DESCRIPTION, model.getTaskDescription());
        assertEquals(TestData.SCHEDULER_TASK_PARAMETERS_JSON, model.getTaskParametersJSON());
        assertTrue(currentUser == model.getUser());
        assertTrue(remoteJob == model.getRemoteJob());
    }

    @Test
    public void shouldConvertToDto() {
        User currentUser = TestDataModels.currentUser();
        User user = TestDataModels.user();
        SchedulerTask schedulerTask = TestDataModels.schedulerTask();
        RemoteJob remoteJob = schedulerTask.getRemoteJob();

        UserDto userDto = TestDataDto.user();
        RemoteJobDto remoteJobDto = TestDataDto.remoteJob();

        when(userDtoConverter.toDto(user)).thenReturn(userDto);
        when(remoteJobDtoConverter.toModel(currentUser, remoteJobDto)).thenReturn(remoteJob);

        SchedulerTaskDto dto = sut.toDto(schedulerTask);
        checkDto(dto);
    }

    @Test
    public void shouldConvertToDtos() {
        User currentUser = TestDataModels.currentUser();
        User user = TestDataModels.user();
        SchedulerTask schedulerTask = TestDataModels.schedulerTask();
        RemoteJob remoteJob = schedulerTask.getRemoteJob();

        UserDto userDto = TestDataDto.user();
        RemoteJobDto remoteJobDto = TestDataDto.remoteJob();

        when(userDtoConverter.toDto(user)).thenReturn(userDto);
        when(remoteJobDtoConverter.toModel(currentUser, remoteJobDto)).thenReturn(remoteJob);

        List<SchedulerTaskDto> models = sut.toDtos(Lists.newArrayList(schedulerTask));

        assertNotNull(models);
        assertTrue(models.size() == 1);

        SchedulerTaskDto dto = models.get(0);
        checkDto(dto);
    }

    private void checkDto(final SchedulerTaskDto dto) {
        assertEquals(TestData.SCHEDULER_TASK_ID, dto.getTaskId());
        assertEquals(TestData.SCHEDULER_TASK_NAME, dto.getTaskName());
        assertEquals(TestData.SCHEDULER_TASK_TYPE, dto.getTaskType());
        assertEquals(TestData.SCHEDULER_TASK_DESCRIPTION, dto.getTaskDescription());
        assertEquals(TestData.SCHEDULER_TASK_PARAMETERS_JSON, dto.getTaskParametersJSON());

        UserDto user = dto.getUser();
        assertEquals(TestData.USER_ID, user.getUserId());
        assertEquals(TestData.USER_NAME, user.getUserName());
    }
}