package EjercicioEntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import objectsPizzeria.Ingredient;

import java.sql.*;

public class EntityManager implements IEntityManager{
    private List<IRunables> runables = new ArrayList<IRunables>();
    private IConfiguration configuration = null;
    
    @Override
    public void save(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(
                this.configuration.getUrl(),
                this.configuration.getUser(),
                this.configuration.getPassword()
            );
            connection.setAutoCommit(false);
            for(IRunables runable: this.runables){
                PreparedStatement statement = connection.prepareStatement(runable.getSQL());
                runable.run(statement);
                statement.executeUpdate();
            }
            connection.commit();
        }
        catch(SQLException e){
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally{
            try {
                if(!connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        
    }
    @Override
    public <T> IEntityManager addStatement(T entity, String sql, Statement<T> statement) {
        IRunables runable = new Runables<T>(sql, entity, statement);
        this.runables.add(runable);
        return this;
    }
    @Override
    public <T> IEntityManager addRangeStatement(Iterable<T> iterable, String sql, Statement<T> statement) {
        for(T t: iterable){
            IRunables runable = new Runables<T>(sql, t, statement);
            this.runables.add(runable);
        }
        return this;
    }
    public static IEntityManager buildConnection(IConfiguration configuration){
        return new EntityManager(configuration);
    }
    private EntityManager(IConfiguration configuration){
        this.configuration = configuration;
    }


    
    public <T> T Select(Class<T> clazz, Resultset<T> resultset){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(
                this.configuration.getUrl(),
                this.configuration.getUser(),
                this.configuration.getPassword()
            );
            
            connection.setAutoCommit(false);
            for(IRunables runable: this.runables){
                PreparedStatement statement = connection.prepareStatement(runable.getSQL());
                runable.run(statement);
                ResultSet resultSet = statement.executeQuery();
            }
            connection.commit();
        }
        catch(SQLException e){
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally{
            try {
                if(!connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Optional<T> optional=null;
        return null;
    }

}