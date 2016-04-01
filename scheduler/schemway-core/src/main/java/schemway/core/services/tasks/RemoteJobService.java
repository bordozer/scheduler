package schemway.core.services.tasks;

import schemway.core.models.RemoteJob;

public interface RemoteJobService {

    RemoteJob findById(Long remoteJobId);
}
