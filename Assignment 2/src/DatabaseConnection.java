import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection createConnection() {
        Connection conn = null;
        try {
            // Establish connection to the database.
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Assign2",
                    "root", ""
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
