package EjercicioEntityManager;

public interface IEntityManager {
    public <T> IEntityManager addStatement(T entity,String sql,Statement<T> statement);
   public <T> IEntityManager addRangeStatemment(Iterable<T> iterable,String sql, Statement<T> statement);
   void save();
}
