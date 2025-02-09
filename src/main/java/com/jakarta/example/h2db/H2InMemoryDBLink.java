package com.jakarta.example.h2db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.tools.Server;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class H2InMemoryDBLink {
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getName());
    public static void verifyDatabase() {
        String url = "jdbc:h2:mem:testdb"; // In-memory database URL
        String user = "sa"; // Default H2 user
        String password = ""; // Default H2 password
        try {
            // Load the H2 JDBC driver
            Class.forName("org.h2.Driver");

            // Create a connection to the in-memory database
            try (Connection conn = DriverManager.getConnection(url, user, password);
                 Statement stmt = conn.createStatement()) {

                // Create a sample table
                stmt.execute("CREATE TABLE PERSONS (ID INT PRIMARY KEY, NAME VARCHAR(255))");

                // Insert some sample data
                stmt.executeUpdate("INSERT INTO PERSONS (ID, NAME) VALUES (1, 'John Doe')");
                stmt.executeUpdate("INSERT INTO PERSONS (ID, NAME) VALUES (2, 'Jane Doe')");

                // Query the sample table and fetch data
                ResultSet rs = stmt.executeQuery("SELECT * FROM PERSONS");
                logger.info("########H2 DB LOGS########");
                while (rs.next()) {
                    logger.info("ID: " + rs.getInt("ID") + ", Name: " + rs.getString("NAME"));
                }
                rs.close();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Server startDatabaseServer() throws SQLException {
        Server webServer = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082");
        return webServer;
    }
}
