package schemway.core.services.tasks;

import org.springframework.stereotype.Service;
import schemway.core.converters.RemoteJobEntityConverter;
import schemway.core.models.RemoteJob;
import schemway.core.repositories.RemoteJobRepository;

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
