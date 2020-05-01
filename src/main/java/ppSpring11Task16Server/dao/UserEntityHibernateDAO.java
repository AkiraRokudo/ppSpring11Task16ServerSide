package ppSpring11Task16Server.dao;

import ppSpring11Task16Server.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author AkiraRokudo on 04.03.2020 in one of sun day
 */
@Repository
public class UserEntityHibernateDAO implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional/*(propagation = Propagation.MANDATORY) ибо сервиса нет, транзактим прямо в дао*/
    public void createObject(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional/*(propagation = Propagation.MANDATORY) ибо сервиса нет, транзактим прямо в дао*/
    public void updateObject(User user) {
        entityManager.merge(user);
    }

    @Override
    public User readObjectById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User readObjectByParameters(String paramName, String paramValue) {
        List<User> users = entityManager.createQuery("SELECT u from User u where u." + paramName + " = ?1")
                .setParameter(1, paramValue).getResultList();
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    @Transactional/*(propagation = Propagation.MANDATORY) ибо сервиса нет, транзактим прямо в дао*/
    public void deleteObject(User user) {
        user = entityManager.find(User.class, user.getId());
        entityManager.remove(user);
    }

    @Override
    public List<User> readAllObject() {
        Query q = entityManager.createQuery("SELECT u FROM User u", User.class);
        List<User> users = q.getResultList();
        return users;
    }

    @Override
    public boolean hasObjectByParameters(String paramName, String paramValue) {
        List<User> users = entityManager.createQuery("SELECT u from User u where u." + paramName + " = ?1", User.class)
                .setParameter(1, paramValue)
                .getResultList();
        return !users.isEmpty();
    }

    @Override
    public User readUserByLogin(String login) {
        return readObjectByParameters("login", login);
    }
}
