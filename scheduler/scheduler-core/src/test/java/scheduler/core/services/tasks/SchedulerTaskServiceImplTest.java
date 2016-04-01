package scheduler.core.services.tasks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import scheduler.core.converters.SchedulerTaskEntityConverter;
import scheduler.core.entities.SchedulerTaskEntity;
import scheduler.core.entities.UserEntity;
import scheduler.core.models.SchedulerTask;
import scheduler.core.models.User;
import scheduler.core.repositories.SchedulerTaskRepository;
import scheduler.core.repositories.UserRepository;
import scheduler.core.utils.TestDataEntities;
import scheduler.core.utils.TestDataModels;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SchedulerTaskServiceImplTest {

    @InjectMocks
    private scheduler.core.services.tasks.SchedulerTaskServiceImpl sut = new scheduler.core.services.tasks.SchedulerTaskServiceImpl();

    @Mock
    private SchedulerTaskEntityConverter schedulerTaskEntityConverter;

    @Mock
    private SchedulerTaskRepository schedulerTaskRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    public void shouldPopulateAndSave(){
        SchedulerTask schedulerTask = TestDataModels.schedulerTask();
        User user = schedulerTask.getUser();

        UserEntity userEntity = TestDataEntities.user();
        SchedulerTaskEntity schedulerTaskEntity = TestDataEntities.schedulerTask();

        when(userRepository.findById(user.getId())).thenReturn(userEntity);
        when(schedulerTaskRepository.findByUserIdAndId(userEntity.getId(), schedulerTaskEntity.getId())).thenReturn(schedulerTaskEntity);
        when(schedulerTaskRepository.saveAndFlush(schedulerTaskEntity)).thenReturn(schedulerTaskEntity);

        sut.modify(user.getId(), schedulerTask);

        verify(schedulerTaskRepository, times(1)).saveAndFlush(schedulerTaskEntity);
    }
}