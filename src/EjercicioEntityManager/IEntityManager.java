package EjercicioEntityManager;

import java.util.Optional;

import objectsPizzeria.Ingredient;

public interface IEntityManager {
    
    public void save();
    public <T> IEntityManager addStatement(final T entity, String sql, Statement<T> statement);
    public <T> IEntityManager addRangeStatement(final Iterable<T> iterable, String sql, Statement<T> statement);
    public <T> Optional<T> Select(Class<T> clazz, Resultset<T> resultset);

}
