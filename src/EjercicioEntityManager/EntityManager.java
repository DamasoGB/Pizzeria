package EjercicioEntityManager;

public class EntityManager implements IEntityManager{
    private IConfiguration configuration;
    public static IEntityManager buildConnection(IConfiguration configuration){
        return new EntityManager(configuration);
    }
    private EntityManager(IConfiguration configuration){
        this.configuration = configuration;
    }
    
    @Override
    public <T> IEntityManager addRangeStatemment(Iterable<T> iterable, String sql, Statement<T> statement) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public void save() {
        // TODO Auto-generated method stub
        
    }
    @Override
    public <T> IEntityManager addStatement(T entity, String sql, Statement<T> statement) {
        // TODO Auto-generated method stub
        return null;
    }
}
