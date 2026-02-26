package database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static final String DB_FOLDER =
            System.getProperty("user.home") + File.separator + ".lifeos";

    private static final String DB_URL =
            "jdbc:sqlite:" + DB_FOLDER + File.separator + "lifeos.db";

    public DatabaseManager() {
        initialize();
    }

    private void initialize() {
        File folder = new File(DB_FOLDER);
        if (!folder.exists()) {
            folder.mkdir();
        }

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS tasks (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    title TEXT NOT NULL,
                    completed INTEGER NOT NULL DEFAULT 0
                )
            """);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}