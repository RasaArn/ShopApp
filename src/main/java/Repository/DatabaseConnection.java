package Repository;

import org.apache.commons.configuration.PropertiesConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private Connection connection;
    private PropertiesConfiguration databaseProperties;

    public DatabaseConnection() {
        try {
            databaseProperties = new PropertiesConfiguration("database.properties");
            String pass = databaseProperties.getString("database.password");
            String user = databaseProperties.getString("database.user");
            String host = databaseProperties.getString("database.host");
            String port = databaseProperties.getString("database.port");
            String dbName = databaseProperties.getString("database.name");

            String connectionUrl = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
            connection = DriverManager.getConnection(connectionUrl, user, pass);

            System.out.println(connectionUrl);
            System.out.println("user: " + user);
            System.out.println("pass: " + pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}