package EjercicioEntityManager;

import java.sql.*;

public class Runables<T> implements IRunables {
    
    private final String sql;
    private final T entity;
    private final Statement<T> statement;

    public Runables(String sql, T entity, Statement<T> statement){
        this.sql = sql;
        this.entity = entity;
        this.statement = statement;
    }

    public String getSQL(){
        return this.sql;
    }
    public void run(PreparedStatement statement) throws SQLException{
        this.statement.run(statement, this.entity);
    }
}

