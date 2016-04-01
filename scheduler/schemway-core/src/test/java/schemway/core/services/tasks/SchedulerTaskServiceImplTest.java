package schemway.core.services.tasks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import schemway.core.converters.SchedulerTaskEntityConverter;
import schemway.core.entities.SchedulerTaskEntity;
import schemway.core.entities.UserEntity;
import schemway.core.models.SchedulerTask;
import schemway.core.models.User;
import schemway.core.repositories.SchedulerTaskRepository;
import schemway.core.repositories.UserRepository;
import schemway.core.utils.TestDataEntities;
import schemway.core.utils.TestDataModels;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SchedulerTaskServiceImplTest {

    @InjectMocks
    private SchedulerTaskServiceImpl sut = new SchedulerTaskServiceImpl();

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