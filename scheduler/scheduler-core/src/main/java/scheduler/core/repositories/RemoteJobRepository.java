package scheduler.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import scheduler.core.entities.RemoteJobEntity;

@Repository
public interface RemoteJobRepository extends JpaRepository<RemoteJobEntity, Long> {

    RemoteJobEntity findById(Long schedulerTaskId);
}
