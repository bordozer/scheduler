package scheduler.app.dao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import scheduler.app.entries.UserEntry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserRepository implements UserDao {

    private static final Logger LOGGER = Logger.getLogger(UserRepository.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    public UserEntry findByLogin(final String login) {
        return new UserEntry("login", "name", "password");
    }
}
