import data.Database;
import data.User;
import data.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MainAdminServlet", value = "/Main-Admin-Servlet")
public class MainAdminServlet extends HttpServlet {
    private UserDAO userDAO;
//    private static Logger logger = LogManager.getLogger();
    public void init() throws ServletException {
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://localhost:1433;database=java_lab9_Users;encrypt=false;user=sa;password=11";
        String username = "sa";
        String password = "11";
        Database database = new Database(driver, url, username, password);
        this.userDAO = new UserDAO(database);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = new ArrayList<>();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
//        logger.info("Adding new user: " + login + ", " + password + ", " + role);
        try {
            users = userDAO.getUsersList();
//            logger.info("usersList size = " + users.size());
        } catch (SQLException e) {
//            logger.error("Can't get users from Database");
            throw new ServletException("Cannot retrieve areas", e);
        }
        try {
            userDAO.addUserByAdmin(login, password, role);
            request.setAttribute("ErrorMessage", "Вы успешно зарегистрировались! Войдите в систему");
//            logger.info("User successfully added!");
        } catch (SQLException e) {
//            logger.error("Can't add user to DB");
            e.printStackTrace();
            request.setAttribute("ErrorMessage", "Логин уже занят");
        }
        var session = request.getSession();
        session.setAttribute("usersList", users);
        System.out.println(users.size());
        request.getRequestDispatcher("lab10views/mainPageAdmin.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = new ArrayList<>();
        String login = request.getParameter("login");
//        logger.info("Try to delete user " + login);
        try {
            users = userDAO.getUsersList();
        } catch (SQLException e) {
            logger.error("Can't get users from Database");
            throw new ServletException("Cannot retrieve areas", e);
        }
        try {
            userDAO.deleteUser(login);
            request.setAttribute("ErrorMessage", "Вы успешно зарегистрировались! Войдите в систему");
        } catch (SQLException e) {
//            logger.error("Can't delete user from DB");
            e.printStackTrace();
            request.setAttribute("ErrorMessage", "Логин уже занят");
        }
        request.setAttribute("usersList", users);
        System.out.println(users.size());
        request.getRequestDispatcher("lab10views/mainPageAdmin.jsp").forward(request, response);
    }
}
