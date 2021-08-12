package SQLPizzeria;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import objectsPizzeria.Comment;

public class CommentDao implements Dao<Comment> {

    Connection conn = null;
	PreparedStatement sentencia = null;
	ResultSet resultSet = null;

    private Connection getConnection() throws SQLException {
        Connection connection;
        connection = ConnectionFactory.getInstance().getConnection();
        return connection;
    }
    
    public void add(Comment comment){
        try {
            String consulta  = """
                            INSERT INTO comment (id, text, rating, date, user) 
                            VALUES (UUID_TO_BIN(?), ?, ?, ?, UUID_TO_BIN(?));
                            """;
            conn = getConnection();
            sentencia= conn.prepareStatement(consulta);
            sentencia.setObject(1, comment.getIdCadena());
            sentencia.setString(2, comment.getTexto());
            sentencia.setInt(3, comment.getScore());
            sentencia.setDate(4, (Date) comment.getFecha());
            sentencia.setObject(5, comment.getUser().getIdCadena()); 

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
            sentencia.setInt(2, comment.getScore());
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
        Optional<Comment> oComment = Optional.of(comment);
        try {
            String consulta  = """
                            SELECT id, text, rating, date, user
                            FROM comment 
                            WHERE id = UUID_TO_BIN(?);
                            """;	
            conn = getConnection();
            sentencia= conn.prepareStatement(consulta);
            sentencia.setObject(1, comment.getIdCadena());
            	              
            resultSet = UnitOfWork.executeQuery(sentencia);

            while(resultSet.next()){
                System.out.println(resultSet.getObject(1));
                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getInt(3));
                System.out.println(resultSet.getDate(4));
                System.out.println(resultSet.getObject(5));
                System.out.println();
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
        return oComment;
    }

}


