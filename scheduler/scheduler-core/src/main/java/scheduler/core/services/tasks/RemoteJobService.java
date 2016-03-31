package scheduler.core.services.tasks;

import scheduler.core.models.RemoteJob;

public interface RemoteJobService {

    RemoteJob findById(Long remoteJobId);
}
