package repositories;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import model.exceptions.CustomException;

public class DatabaseConnection {
    
    private static Properties properties = new Properties();
    private static Connection connection = null;
    
    private static void setProperties() {
        try (FileReader file = new FileReader("db.properties")) {
            properties.load(file);
            file.close();
        } catch (IOException e) {
            throw new CustomException("Properties exception. " + e.getMessage());
        }
    }


    public static Connection getConnection() {
        setProperties();
        String dbUser=properties.getProperty("dbUser");
        String dbPassword = properties.getProperty("dbPassword");
        String dbUrl = properties.getProperty("dbUrl");
        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);   
            return connection;
        } catch (Exception e) {
            throw new CustomException("Database connection exception: " + e.getMessage());
        }
    }


    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new CustomException("Database close connection exception: " + e.getMessage());
        }
    }
    

}



