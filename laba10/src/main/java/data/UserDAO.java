package data;

import javax.xml.crypto.Data;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Database database;
    public UserDAO(Database database){
        this.database = database;
    }
    public List<User> getUsersList() throws SQLException{
        Connection connection = null;
        PreparedStatement query = null;
        ResultSet queryResult = null;
        List<User> usersList = new ArrayList<>();
        try {
            connection = database.getConnection();
//            query = connection.prepareStatement("select name, role, password from InfoUsers");
            query = connection.prepareStatement("use Java_lab9_Users\n" +
                    "select name,password,role from InfoUsers ");
            queryResult = query.executeQuery();

            while (queryResult.next()){
                User area = new User(queryResult.getString("name"),queryResult.getString("password"),queryResult.getString("role"));
                usersList.add(area);
            }
        }
        finally {
            if (queryResult != null) try {queryResult.close();} catch (SQLException logOrIgnore){}
            if (query != null) try {query.close();} catch (SQLException logOrIgnore){}
            if (connection!= null) try {connection.close();} catch (SQLException logOrIgnore){}
        }
        return usersList;
    }

    public void addUser(String name, String password) throws SQLException{
        Connection connection = null;
        Statement statement = null;
        String encryptedPass = encryptPassword(password);
        connection = database.getConnection();
        statement = connection.createStatement();
        statement.execute("insert into InfoUsers " +
        "values ('" +  name +"', '" + encryptedPass + "', 'user')");
    }
    public void addUserByAdmin(String name, String password, String role) throws SQLException{
        Connection connection = null;
        Statement statement = null;
        String encryptedPass = encryptPassword(password);
        connection = database.getConnection();
        statement = connection.createStatement();
        statement.execute("insert into InfoUsers " +
                "values ('" +  name +"', '" + encryptedPass + "', '"+ role + "')");
    }
    public void deleteUser(String name) throws SQLException {
        var connection = database.getConnection();
        var statement= connection.createStatement();
        statement.executeUpdate("delete from InfoUsers where name = '" +  name + "'");
    }
    private String encryptPassword(String password){
        String encryptedPassword = null;
        try
        {
            /* MessageDigest instance for MD5. */
            MessageDigest m = MessageDigest.getInstance("MD5");
            /* Add plain-text password bytes to digest using MD5 update() method. */
            m.update(password.getBytes());
            /* Convert the hash value into bytes */
            byte[] bytes = m.digest();
            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */
            StringBuilder s = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            /* Complete hashed password in hexadecimal format */
            encryptedPassword = s.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return encryptedPassword;
    };
}