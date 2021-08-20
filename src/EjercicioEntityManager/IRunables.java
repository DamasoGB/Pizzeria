package EjercicioEntityManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface IRunables {
    public String getSQL();
    public void run(final PreparedStatement statement) throws SQLException;
}
