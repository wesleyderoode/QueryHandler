package nl.wderoode.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SimpleDataSource {

    private String dbserver;
    private String database;
    private String username;
    private String password;

    private Connection connection;

    private void init() {

        // load driver
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }

        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(new File("src/main/resources/database.properties")));
            this.dbserver = properties.getProperty("dbserver");
            this.database = properties.getProperty("database");
            this.username = properties.getProperty("username");
            this.password = properties.getProperty("password");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    private Connection createConnection() throws SQLException {
        String connectionString = "jdbc:mysql://" + dbserver + "/" +
                database + "?" + "user=" + username + "&password="
                + password + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        return connection = DriverManager.getConnection(connectionString);
    }

    public Connection getConnection() throws SQLException {
        if (connection == null) {
            init();
            connection = createConnection();
        }

        return connection;
    }
}
