package ppSpring11Task16Server.dao;

import ppSpring11Task16Server.model.Role;

import java.util.Set;

/**
 * @author AkiraRokudo on 07.04.2020 in one of sun day
 */
public interface RoleDAO extends ObjectDAO<Role> {


    Set<Role> getRolesByNames(Set<String> roleNames);
}
