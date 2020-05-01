package ppSpring11Task16Server.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * @author AkiraRokudo on 04.03.2020 in one of sun day
 */
public interface ObjectDAO<T> {

    void createObject(T t) throws SQLException;

    void updateObject(T t) throws SQLException;

    T readObjectById(Long id) throws SQLException;

    T readObjectByParameters(String paramName, String paramValue) throws SQLException;

    void deleteObject(T t) throws SQLException;

    List<T> readAllObject() throws SQLException;

    boolean hasObjectByParameters(String paramName, String paramValue) throws SQLException;

}
