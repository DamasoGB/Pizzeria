package PizzeriaDao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import objectsPizzeria.Comment;

public class CommentDao implements Dao<Comment> {

    Connection conn = null;
	PreparedStatement sentencia = null;
	ResultSet resultSet = null;
    List<Comment> comments = new ArrayList<Comment>();

    private Connection getConnection() throws SQLException {
        Connection connection;
        connection = ConnectionFactory.getInstance().getConnection();
        return connection;
    }
    
    public void add(Comment comment){
        try {
            String consulta  = """
                            INSERT INTO comment (id, text, rating, date, id_user,id_pizza) 
                            VALUES (UUID_TO_BIN(?), ?, ?, ?, UUID_TO_BIN(?), UUID_TO_BIN(?));
                            """;
            conn = getConnection();
            sentencia= conn.prepareStatement(consulta);
            sentencia.setObject(1, comment.getIdCadena());
            sentencia.setString(2, comment.getTexto());
            sentencia.setFloat(3, comment.getScore());
            sentencia.setDate(4, (Date) comment.getFecha());
            sentencia.setObject(5, comment.getUser().getIdCadena());
            sentencia.setObject(6, comment.getPizza().getIdCadena());

            UnitOfWork.executeNonQuery(sentencia);
            
        } catch (SQLException e) {
             e.printStackTrace();
        } finally {
			try {
				if (sentencia != null)
					conn.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
        
    }
    public void update(Comment comment){
        try {
            String consulta  = """
                        UPDATE comment
                        SET text = ?, rating = ? 
                        WHERE id = UUID_TO_BIN(?);
                            """;
            conn = getConnection();
            sentencia= conn.prepareStatement(consulta);
            sentencia.setString(1, comment.getTexto());
            sentencia.setFloat(2, comment.getScore());
            sentencia.setObject(5, comment.getIdCadena());

            UnitOfWork.executeNonQuery(sentencia);
            
        } catch (SQLException e) {
             e.printStackTrace();
        }finally {
			try {
				if (sentencia != null)
					conn.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
    }
    public void delete(Comment comment){
        try {
            String consulta  = """
                            DELETE FROM comment 
                            WHERE id = UUID_TO_BIN(?);
                            """;
            conn = getConnection();	
            sentencia= conn.prepareStatement(consulta);
            sentencia.setObject(1, comment.getIdCadena());

            UnitOfWork.executeNonQuery(sentencia);
            
        } catch (SQLException e) {
             e.printStackTrace();
        }finally {
			try {
				if (sentencia != null)
					conn.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
    }
    public Optional<Comment> get(Comment comment){
        
        try {
            String consulta  = """
                            SELECT BIN_TO_UUID(id), text, rating, date, BIN_TO_UUID(id_user), BIN_TO_UUID(id_pizza)
                            FROM comment 
                            WHERE id = UUID_TO_BIN(?);
                            """;	
            conn = getConnection();
            sentencia= conn.prepareStatement(consulta);
            sentencia.setObject(1, comment.getIdCadena());
            	              
            resultSet = UnitOfWork.executeQuery(sentencia);

            while(resultSet.next()){
                comment.generateStringUUID(resultSet.getString(1));
                comment.setTexto(resultSet.getString(2));
                comment.setScore(resultSet.getInt(3));
                comment.setFecha(resultSet.getDate(4));
                comment.setUser(resultSet.getString(5));
                comment.setPizza(resultSet.getString(6));
            }
            
        } catch (SQLException e) {
             e.printStackTrace();
        } finally {
			try {
                if(resultSet != null)
                    conn.close();
				if (sentencia != null)
					conn.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
        Optional<Comment> oComment = Optional.of(comment);
        return oComment;
    }

    public List<Comment> getAll() {
        try {
            String consulta  = """
                        SELECT id, text, rating, date, id_user, id_pizza
                        FROM comment;
                            """;	
            conn = getConnection();
            sentencia= conn.prepareStatement(consulta);
	              
            resultSet = UnitOfWork.executeQuery(sentencia);

            while(resultSet.next()){
                Comment comment = new Comment(resultSet.getString("text"),
                                            resultSet.getFloat("rating"));
                comment.setUser(resultSet.getString("BIN_TO_UUID(id_user)"));
                comment.setPizza(resultSet.getString("BIN_TO_UUID(id_pizza)"));
                comment.generateStringUUID(resultSet.getString("BIN_TO_UUID(id)"));
                comments.add(comment);
            }
            
        } catch (SQLException e) {
             e.printStackTrace();
        } finally {
			try {
                if(resultSet != null)
                    conn.close();
				if (sentencia != null)
					conn.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        return comments;
    }

}


