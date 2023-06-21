package data;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private String url;
    private String userName;
    private String password;

    public Database(String driver,String url, String userName, String password) {
        try{
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException| InvocationTargetException ex){
           throw new RuntimeException("Driver class is missing in classpath", ex);
        }
        this.url = url;
        this.userName = userName;
        this.password = password;
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }
}
