package scheduler.app.converters;

import com.google.inject.Inject;
import scheduler.app.dao.UserRepository;
import scheduler.app.entries.SchedulerTaskEntry;
import scheduler.app.entries.UserEntry;
import scheduler.app.models.SchedulerTask;

public class SchedulerTaskConverterServiceImpl implements SchedulerTaskConverterService {

    @Inject
    private UserConvectionService userConvectionService;

    @Inject
    private UserRepository userRepository;

    @Override
    public SchedulerTaskEntry populateEntity(final SchedulerTaskEntry entity, final SchedulerTask model) {
        entity.setId(model.getId());
        entity.setUser(getUser(model.getId()));
        entity.setTaskName(model.getTaskName());
        entity.setTaskDescription(model.getTaskDescription());
        return entity;
    }

    @Override
    public SchedulerTask toModel(final SchedulerTaskEntry entity) {
        SchedulerTask model = new SchedulerTask();
        model.setId(entity.getId());
        model.setUser(userConvectionService.toModel(getUser(model.getId())));
        model.setTaskName(entity.getTaskName());
        model.setTaskDescription(entity.getTaskDescription());
        return model;
    }

    private UserEntry getUser(final Long userId) {
        return userRepository.findById(userId);
    }
}
