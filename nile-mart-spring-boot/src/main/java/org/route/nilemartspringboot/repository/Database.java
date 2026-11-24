package org.route.nilemartspringboot.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Provides JDBC connections to the Oracle database.
 *
 * <p>Requires the Oracle JDBC driver dependency:
 * <pre>{@code
 * <groupId>com.oracle.database.jdbc</groupId>
 * <artifactId>ojdbc11</artifactId>
 * }</pre>
 */
public final class Database {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521/FREE";
    private static final String USERNAME = "SYSTEM";
    private static final String PASSWORD = "1234";

    private Database() {}

    /**
     * Opens a new database connection. The caller is responsible for closing it.
     *
     * @return a new connection
     * @throws SQLException if the connection cannot be opened
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
