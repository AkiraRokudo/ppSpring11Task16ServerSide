package ppSpring11Task16Server.dao;

import ppSpring11Task16Server.model.User;

import java.sql.SQLException;

/**
 * @author AkiraRokudo on 07.04.2020 in one of sun day
 */
public interface UserDAO extends ObjectDAO<User> {

    public User readUserByLogin(String login) throws SQLException;
}
