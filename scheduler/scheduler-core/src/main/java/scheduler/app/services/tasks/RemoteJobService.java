package scheduler.app.services.tasks;

import scheduler.app.models.RemoteJob;

public interface RemoteJobService {

    RemoteJob findById(Long remoteJobId);
}
