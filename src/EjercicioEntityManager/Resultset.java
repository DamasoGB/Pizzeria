package EjercicioEntityManager;

import java.sql.SQLException;
import java.sql.ResultSet;

public interface Resultset<T> {
    void run(ResultSet resultSet, T entity) throws SQLException;
}
