import data.Database;
import data.TgChannel;
import data.TgChannelDAO;
import data.UserDAO;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Main-User-Servlet")
public class MainUserServlet extends HttpServlet {
    private UserDAO userDAO;
    private TgChannelDAO tgChannelDAO;
    @Override
    public void init(ServletConfig config) throws ServletException {
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://localhost:1433;database=Java_lab9_Users;encrypt=false;user=sa;password=11";
        String username = "sa";
        String password = "11";
        Database database = new Database(driver, url, username, password);
        this.tgChannelDAO = new TgChannelDAO(database);
//        BasicConfigurator.configure();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var channelToDelete = req.getParameter("channelName");
        List<TgChannel> channels = new ArrayList<>();
        try {
            channels = tgChannelDAO.getChannels();
        } catch (SQLException e) {
            var writer = resp.getWriter();
            writer.println("Cannot retrieve channels");
            throw  new ServletException("Cannot retrieve channels", e);
        }
        if (channelToDelete != null) {
            try {
                tgChannelDAO.deleteChannel(channelToDelete);
            } catch (SQLException e) {
                throw new RuntimeException("Cannot delete channel", e);
            }
        }
        req.setAttribute("channelsList",channels);
        req.getRequestDispatcher("lab10views/userMainPage.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var channelName = req.getParameter("channelName");
        var subscribersAmount = req.getParameter("subscribersAmount");
        List<TgChannel> channels = new ArrayList<>();
        try {
            channels = tgChannelDAO.getChannels();
        } catch (SQLException e) {
            var writer = resp.getWriter();
            writer.println("Cannot retrieve channels");
            throw  new ServletException("Cannot retrieve channels", e);
        }
        try{
            tgChannelDAO.addChannel(channelName, Integer.parseInt(subscribersAmount));
        } catch (SQLException e) {
            var writer = resp.getWriter();
            writer.println("Cannot retrieve channels");
            throw  new ServletException("Cannot retrieve channels", e);
        }
        var session = req.getSession();
        session.setAttribute("channelsList",channels);
        req.getRequestDispatcher("lab10views/userMainPage.jsp").forward(req,resp);
    }
}