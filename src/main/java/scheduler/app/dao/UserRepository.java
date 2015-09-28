package scheduler.app.dao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import scheduler.app.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserRepository implements UserDao {

	private static final Logger LOGGER = Logger.getLogger( UserRepository.class );

	@PersistenceContext
	private EntityManager em;

	@Override
	public User findByLogin( final String login ) {
		return null;
	}
}
