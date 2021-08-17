package EjercicioEntityManager;

import java.sql.PreparedStatement;

public interface Statement<T> {
    void method(PreparedStatement statement,T entity);
}
