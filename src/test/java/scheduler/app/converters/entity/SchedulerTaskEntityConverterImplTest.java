package scheduler.app.converters.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import scheduler.app.entities.RemoteJobEntity;
import scheduler.app.entities.SchedulerTaskEntity;
import scheduler.app.entities.UserEntity;
import scheduler.app.models.RemoteJob;
import scheduler.app.models.SchedulerTask;
import scheduler.app.repositories.UserRepository;
import scheduler.app.utils.TestData;
import scheduler.app.utils.TestDataEntities;
import scheduler.app.utils.TestDataModels;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SchedulerTaskEntityConverterImplTest {

    @InjectMocks
    private SchedulerTaskEntityConverterImpl sut = new SchedulerTaskEntityConverterImpl();

    @Spy
    private RemoteJobEntityConverter remoteJobEntityConverter = new RemoteJobEntityConverterImpl();

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserEntityConverter userEntityConverter;

    @Test
    public void shouldPopulateEntity() {
        final SchedulerTask schedulerTask = TestDataModels.schedulerTask();

        final UserEntity userEntity = TestDataEntities.user();

        SchedulerTaskEntity entity = new SchedulerTaskEntity();
        final RemoteJobEntity remoteJobEntity = new RemoteJobEntity();
        entity.setRemoteJob(remoteJobEntity);
        remoteJobEntity.setSchedulerTask(entity);
        when(userRepository.findById(TestData.USER_ID)).thenReturn(userEntity);

        sut.populateEntity(entity, schedulerTask);

        assertEquals(TestData.SCHEDULER_TASK_ID, entity.getId());
        assertEquals(TestData.SCHEDULER_TASK_NAME, entity.getTaskName());
        assertEquals(TestData.SCHEDULER_TASK_TYPE, entity.getTaskType());
        assertEquals(TestData.SCHEDULER_TASK_DESCRIPTION, entity.getTaskDescription());
        assertEquals(TestData.SCHEDULER_TASK_PARAMETERS_JSON, entity.getTaskParametersJSON());

        final RemoteJobEntity remoteJob = entity.getRemoteJob();
        assertEquals(TestData.REMOTE_JOB_ID, remoteJob.getId());
        assertEquals(TestData.REMOTE_JOB_REQUEST_URL, remoteJob.getRequestUrl());
        assertEquals(TestData.REMOTE_JOB_REQUEST_METHOD, remoteJob.getRequestMethod());
        assertEquals(TestData.REMOTE_JOB_AUTH_STRING, remoteJob.getAuthString());
        assertEquals(TestData.REMOTE_JOB_POST_JSON, remoteJob.getPostJson());
    }

    @Test
    public void shouldConvertEntityToModel() {
        final SchedulerTaskEntity entity = TestDataEntities.schedulerTask();

        when(userEntityConverter.toModel(entity.getUser())).thenReturn(TestDataModels.user());

        final SchedulerTask model = sut.toModel(entity);

        assertEquals(TestData.SCHEDULER_TASK_ID, model.getId());assertEquals(TestData.SCHEDULER_TASK_NAME, entity.getTaskName());
        assertEquals(TestData.SCHEDULER_TASK_TYPE, model.getTaskType());
        assertEquals(TestData.SCHEDULER_TASK_DESCRIPTION, model.getTaskDescription());
        assertEquals(TestData.SCHEDULER_TASK_PARAMETERS_JSON, model.getTaskParametersJSON());

        final RemoteJob remoteJob = model.getRemoteJob();
        assertEquals(TestData.REMOTE_JOB_ID, remoteJob.getId());
        assertEquals(TestData.REMOTE_JOB_REQUEST_URL, remoteJob.getRequestUrl());
        assertEquals(TestData.REMOTE_JOB_REQUEST_METHOD, remoteJob.getRequestMethod());
        assertEquals(TestData.REMOTE_JOB_AUTH_STRING, remoteJob.getAuthString());
        assertEquals(TestData.REMOTE_JOB_POST_JSON, remoteJob.getPostJson());
    }
}