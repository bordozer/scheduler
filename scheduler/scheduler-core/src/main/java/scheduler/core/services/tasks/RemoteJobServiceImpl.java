package scheduler.core.services.tasks;

import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class RemoteJobServiceImpl implements RemoteJobService {

    @Inject
    private scheduler.core.repositories.RemoteJobRepository remoteJobRepository;

    @Inject
    private scheduler.core.converters.entity.RemoteJobEntityConverter remoteJobEntityConverter;

    @Override
    public scheduler.core.models.RemoteJob findById(final Long remoteJobId) {
        return remoteJobEntityConverter.toModel(remoteJobRepository.findById(remoteJobId));
    }
}
