package SQLPizzeria;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    List<T> getAll();
    
    Optional<T> get(T t);
    
    void add(T t);
    
    void update(T t);
    
    void delete(T t);
}
