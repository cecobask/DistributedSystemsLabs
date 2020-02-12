import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection createConnection() {
        Dotenv dotenv = Dotenv.load(); // Load environment variables.
        Connection conn = null;
        try {
            // Establish connection to the database.
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dotenv.get("DATABASE_NAME"),
                    dotenv.get("USER_NAME"), ""
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
