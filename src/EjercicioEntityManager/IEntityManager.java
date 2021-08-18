package EjercicioEntityManager;

import java.sql.ResultSet;

public interface IEntityManager {
    
    public void save();
    public <T> IEntityManager addStatement(final T entity, String sql, Statement<T> statement);
    public <T> IEntityManager addRangeStatement(final Iterable<T> iterable, String sql, Statement<T> statement);
    public <T> T Select(ResultSet resultSet);

    //TODO: meter un ResultSet como argumento
    //TODO: ejecutar el select
}
