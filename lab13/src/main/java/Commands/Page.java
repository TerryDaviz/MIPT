package Commands;

import DB.DBConnection;
import Model.TgChannel;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Page {
    public static void PageCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var dbContext = new DBConnection();
        ArrayList<TgChannel> channels = null;
        int countChannels= 0;
        try {
            channels = dbContext.GetAllTgChannels();
            countChannels = channels.size();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        req.setAttribute("channels",channels);
        req.setAttribute("user","user");
        req.getServletContext().getRequestDispatcher("/page.jsp").forward(req,resp);
    }
}
