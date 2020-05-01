package ppSpring11Task16Server.dao;

import ppSpring11Task16Server.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author AkiraRokudo on 07.04.2020 in one of sun day
 */
@Repository
public class RoleEntityHibernateDAO implements RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createObject(Role role) throws SQLException {
        throw new UnsupportedOperationException("Низя. Идите лесом, или к админу БД");
    }

    @Override
    public void updateObject(Role role) throws SQLException {
        throw new UnsupportedOperationException("Низя. Идите лесом, или к админу БД");
    }

    @Override
    public Role readObjectById(Long id) throws SQLException {
        throw new UnsupportedOperationException("Низя. Идите лесом, или к админу БД");
    }

    @Override
    public Role readObjectByParameters(String paramName, String paramValue) throws SQLException {
        throw new UnsupportedOperationException("Низя. Идите лесом, или к админу БД");
    }

    @Override
    public void deleteObject(Role role) throws SQLException {
        throw new UnsupportedOperationException("Низя. Идите лесом, или к админу БД");
    }

    @Override
    public List<Role> readAllObject() throws SQLException {
        Query q = entityManager.createQuery("SELECT r FROM Role r", Role.class);
        List<Role> roles = q.getResultList();
        return roles;
    }

    @Override
    public boolean hasObjectByParameters(String paramName, String paramValue) throws SQLException {
        throw new UnsupportedOperationException("Низя. Идите лесом, или к админу БД");
    }

    @Override
    public Set<Role> getRolesByNames(Set<String> roleNames) {
        Set<Role> roles = entityManager.createQuery("SELECT r from Role r where r.name IN (:names)", Role.class)
                .setParameter("names", roleNames)
                .getResultList().stream().collect(Collectors.toSet());
        return roles;
    }
}
