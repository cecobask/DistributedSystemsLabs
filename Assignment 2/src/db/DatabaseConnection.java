package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Tsvetoslav Dimov
 * Student ID: 20077038
 * Module: Distributed Systems
 */
public class DatabaseConnection {

    /**
     * Establish MySQL database connection using JDBC.
     * Connects to Assign2 database on port 3306.
     * @return Connection
     */
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
