package Commands;

import DB.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public class DelChannel {
    public static void DeleteChannelCommand(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        var dbContext = new DBConnection();
        var name = req.getParameter("channelToDelete");
        try{
            dbContext.remove(name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("controller?command=page").forward(req,resp);
    }
}
