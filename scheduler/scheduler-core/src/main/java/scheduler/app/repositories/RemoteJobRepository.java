package scheduler.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import scheduler.app.entities.RemoteJobEntity;

@Repository
public interface RemoteJobRepository extends JpaRepository<RemoteJobEntity, Long> {

    RemoteJobEntity findById(Long schedulerTaskId);
}
