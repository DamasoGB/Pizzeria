package SQLPizzeria;

import java.util.Optional;

public interface Dao<T> {
    
    Optional<T> get(T t);
    
    void add(T t);
    
    void update(T t);
    
    void delete(T t);
}
