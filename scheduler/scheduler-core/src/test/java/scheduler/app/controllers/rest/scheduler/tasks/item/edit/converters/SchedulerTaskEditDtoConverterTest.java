package scheduler.app.controllers.rest.scheduler.tasks.item.edit.converters;

import com.beust.jcommander.internal.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import scheduler.app.controllers.rest.scheduler.tasks.item.edit.dto.RemoteJobEditDto;
import scheduler.app.controllers.rest.scheduler.tasks.item.edit.dto.SchedulerTaskEditDto;
import scheduler.app.models.RemoteJob;
import scheduler.app.models.SchedulerTask;
import scheduler.app.models.User;
import scheduler.app.utils.TestData;
import scheduler.app.utils.TestDataDto;
import scheduler.app.utils.TestDataModels;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SchedulerTaskEditDtoConverterTest {

    @InjectMocks
    private SchedulerTaskEditDtoConverter sut = new SchedulerTaskEditDtoConverter();

    @Mock
    private RemoteJobEditDtoConverter remoteJobEditDtoConverter;

    @Test
    public void shouldConvertToModel() {
        User currentUser = TestDataModels.currentUser();
        SchedulerTaskEditDto dto = TestDataDto.schedulerTaskEdit();
        RemoteJobEditDto remoteJobEditDto = TestDataDto.remoteJobEdit();
        RemoteJob remoteJob = TestDataModels.remoteJob();

        when(remoteJobEditDtoConverter.toModel(currentUser, remoteJobEditDto)).thenReturn(remoteJob);

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
        SchedulerTask schedulerTask = TestDataModels.schedulerTask();
        RemoteJob remoteJob = schedulerTask.getRemoteJob();
        RemoteJobEditDto remoteJobEditDto = TestDataDto.remoteJobEdit();

        when(remoteJobEditDtoConverter.toDto(remoteJob)).thenReturn(remoteJobEditDto);

        SchedulerTaskEditDto dto = sut.toDto(schedulerTask);
        checkDto(dto);
    }

    @Test
    public void shouldConvertToDtos() {
        SchedulerTask schedulerTask = TestDataModels.schedulerTask();
        RemoteJob remoteJob = schedulerTask.getRemoteJob();
        RemoteJobEditDto remoteJobEditDto = TestDataDto.remoteJobEdit();

        when(remoteJobEditDtoConverter.toDto(remoteJob)).thenReturn(remoteJobEditDto);

        List<SchedulerTaskEditDto> models = sut.toDtos(Lists.newArrayList(schedulerTask));

        assertNotNull(models);
        assertTrue(models.size() == 1);

        SchedulerTaskEditDto dto = models.get(0);
        checkDto(dto);
    }

    private void checkDto(final SchedulerTaskEditDto dto) {
        assertEquals(TestData.SCHEDULER_TASK_ID, dto.getTaskId());
        assertEquals(TestData.SCHEDULER_TASK_NAME, dto.getTaskName());
        assertEquals(TestData.SCHEDULER_TASK_TYPE, dto.getTaskType());
        assertEquals(TestData.SCHEDULER_TASK_DESCRIPTION, dto.getTaskDescription());
        assertEquals(TestData.SCHEDULER_TASK_PARAMETERS_JSON, dto.getTaskParametersJSON());
    }
}