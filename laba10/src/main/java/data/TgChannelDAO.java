package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TgChannelDAO {
    private Database db;
    public TgChannelDAO(Database db) {
        this.db = db;
    }
    public List<TgChannel> getChannels() throws SQLException{
        var channelList = new ArrayList<TgChannel>();
        Connection connection = null;
        PreparedStatement query = null;
        ResultSet queryResult = null;
        try {
                connection = db.getConnection();
                query = connection.prepareStatement("select channelName, subscribersAmount from TgChannels");
                queryResult = query.executeQuery();
                while(queryResult.next()){
                    var channel = new TgChannel(queryResult.getString("channelName"),queryResult.getInt("subscribersAmount"));
                    channelList.add(channel);
                }
            }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        finally {
            if (queryResult != null) try {queryResult.close();} catch (SQLException logOrIgnore){};
            if (query != null) try {query.close();} catch (SQLException logOrIgnore){};
            if (connection != null) try {connection.close();} catch (SQLException logOrIgnore){};
        }
        return channelList;
    }
    public void addChannel(String name, int subscribersAmount) throws SQLException {
        var connection = db.getConnection();
        var statement = connection.prepareStatement(String.format("insert into TgChannels values ('%s', %d)",name,subscribersAmount));
        statement.executeUpdate();
    }
    public void deleteChannel(String name) throws SQLException {
       var connection = db.getConnection();
       var statement = connection.prepareStatement(String.format("delete from TgChannels where channelName = '%s'",name));
       statement.executeUpdate();
    };
}
