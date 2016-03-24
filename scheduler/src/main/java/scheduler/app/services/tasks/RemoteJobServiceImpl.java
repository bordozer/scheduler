package scheduler.app.services.tasks;

import org.springframework.stereotype.Service;
import scheduler.app.converters.entity.RemoteJobEntityConverter;
import scheduler.app.entities.RemoteJobEntity;
import scheduler.app.models.RemoteJob;
import scheduler.app.repositories.RemoteJobRepository;

import javax.inject.Inject;

@Service
public class RemoteJobServiceImpl implements RemoteJobService {

    @Inject
    private RemoteJobRepository remoteJobRepository;

    @Inject
    private RemoteJobEntityConverter remoteJobEntityConverter;

    @Override
    public RemoteJob findById(final Long remoteJobId) {
        return remoteJobEntityConverter.toModel(remoteJobRepository.findById(remoteJobId));
    }
}
