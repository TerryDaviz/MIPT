package Commands;

import DB.DBConnection;
import Model.TgChannel;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public class AddChannel{
    public static void AddChannelCommand(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        var name = req.getParameter("name");
        var subscribers = req.getParameter("subscribers");
        var dbContext = new DBConnection();
        if (name.equals("") || subscribers.equals("")){
           req.setAttribute("error","cannot add empty channel");
           req.getRequestDispatcher("controller?command=page").forward(req,resp);
        }
        else {
            try {
                var channel = new TgChannel(name, subscribers);
                dbContext.AddTgChannels(channel);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            req.getRequestDispatcher("controller?command=page").forward(req,resp);
        }
    }
}
