package DB;

import Model.TgChannel;
import Model.Users;

import java.sql.*;
import java.util.ArrayList;

public class DBConnection {
    private Connection connection;
    private Statement statement ;
    public Connection getDbConnection()throws SQLException{
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").getDeclaredConstructor().newInstance();
//            String url = "jdbc:sqlserver://localhost:1433;databaseName="
            var url =  "jdbc:sqlserver://localhost:1433;database=java_lab9_Users;encrypt=false;user=sa;password=11";
            connection = (Connection) DriverManager.getConnection(url,"sa","11");
            System.out.println("Successful connection");
        }catch (Exception ex){
            System.out.println("Connection failed");
            System.out.println(ex.getMessage());
        }
        return connection;
    }
    public ArrayList<Users> GetAllUsers()throws SQLException{
        var query = "select * from InfoUsers";
        statement = connection.createStatement();
        var resultSet = statement.executeQuery(query);
        ArrayList<Users> users = new ArrayList<>();
        while (resultSet.next()){
            var userName = resultSet.getString("name");
            var password = resultSet.getString("password");
            var role = resultSet.getString("role");
            Users user = new Users(userName,password,role);
            users.add(user);
        }
        return  users;
    }
    public Users GetByUsername(String Username) throws SQLException {
        String select ="select * from InfoUsers where name = '"+Username+"';";
        statement = getDbConnection().createStatement();
        ResultSet resultSet= statement.executeQuery(select);
        Users user=null;
        if(resultSet != null){
            while(resultSet.next()){
                String username = resultSet.getString("name");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                user = new Users(username,password,role);
            }
        }
        return  user;
    }
    public boolean AddUser(Users user) throws SQLException {
        //проверка на существующее имя пользователя
        String select="insert into InfoUsers values ('"+user.getUsername() +"','"+user.getPassword()+ "','user');";
        statement = getDbConnection().createStatement();
        statement.executeUpdate(select);
        return true;
    }
    public ArrayList<TgChannel> GetAllTgChannels() throws SQLException {
        String sqlQuery="select * from TgChannels";
        statement = getDbConnection().createStatement();
        ResultSet resultSet=statement.executeQuery(sqlQuery);

        ArrayList<TgChannel> nations = new ArrayList<TgChannel>();
        while(resultSet.next()){
            String name = resultSet.getString("channelName");
            String country = resultSet.getString("subscribersAmount");
            TgChannel nation = new TgChannel(name,country);
            nations.add(nation);
        }
        return nations;
    }
    public void AddTgChannels(TgChannel nat) throws SQLException {
//        logger.info("Add new channel");
        String select="insert TgChannels values('"+nat.getName()+"','"+nat.getAmount()+"')";
        statement = getDbConnection().createStatement();
        statement.executeUpdate(select);
    }
    public void remove(String names) throws SQLException {
//        logger.info("Delete channel");
        String select="DELETE FROM TgChannels WHERE channelName ='"+names+"'";
        statement = getDbConnection().createStatement();
        statement.executeUpdate(select);
    }
    public void update(String names,String number) throws SQLException {
//        logger.info("Изменение народа");
        String select="UPDATE TgChannels SET subscribersAmount='"+number+"' WHERE channelName='"+names+"';";
        statement = getDbConnection().createStatement();
        statement.executeUpdate(select);
    }
}
