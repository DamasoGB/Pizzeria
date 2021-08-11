package SQLPizzeria;

public interface Dao<T> {
    
    void getAll(T t);
    
    void add(T t);
    
    void update(T t);
    
    void delete(T t);
}
