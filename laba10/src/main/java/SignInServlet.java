import com.microsoft.sqlserver.jdbc.StringUtils;
import data.Database;
import data.User;
import data.UserDAO;
import data.Utils;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/Sign-In-Servlet")
public class SignInServlet extends HttpServlet {
    private UserDAO userDAO;
    public List<User> userList = new ArrayList<>();
    @Override
    public void init(ServletConfig config) throws ServletException {
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://localhost:1433;database=Java_lab9_Users;encrypt=false;user=sa;password=11";
        String username = "sa";
        String password = "11";
        Database database = new Database(driver, url, username, password);
        this.userDAO = new UserDAO(database);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Date currentDate = new Date();
        boolean isFoundUser = false;
        boolean isWrongPassword = false;
        boolean isNotFoundUser = false;
        String loginCookie = "testLogin";
        String roleCookie = "testRole";
        try { userList = userDAO.getUsersList(); }
        catch (SQLException e) {
            throw new ServletException("Cannot retrieve userList", e);
        }
        String login = req.getParameter("login");
        String password = Utils.encryptPassword(req.getParameter("password"));
        HttpSession session = req.getSession();
        session.setAttribute("userSignedIn", null);
//        logger.debug("login = " + login + ", pass = " + password);
        for (User user : userList) {
            if (user.getUserName().equals(login) && !user.getUserPassword().equals(password)) {
                isWrongPassword = true;
                req.setAttribute("ServletResponse", "Неверный пароль");
                session.setAttribute("userSignedIn", null);
                break;
            }
            else if (user.getUserName().equals(login) && user.getUserPassword().equals(password)) {
                req.setAttribute("name", user.getUserName());
                req.setAttribute("role", user.getUserRole());
                req.setAttribute("date", currentDate);
                req.setAttribute("ServletResponse", "");
                req.setAttribute("usersList", userList);
                session.setAttribute("usersList", userList);
                session.setAttribute("userSignedIn", user.getUserName());
                isFoundUser = true;
                loginCookie = user.getUserName();
                roleCookie = user.getUserRole();
                break;
            }
            else {
                isNotFoundUser = true;
                session.setAttribute("userSignedIn", null);
                req.setAttribute("ServletResponse", "Пользователя не существует");
            }
        }
        if (StringUtils.isEmpty(login) || StringUtils.isEmpty(password)) {
            req.setAttribute("ServletResponse", "Заполните все поля");
            req.getRequestDispatcher("lab10views/signInForm.jsp").forward(req, resp);
        }
        Cookie cookieLogin = new Cookie("login", loginCookie);
        Cookie cookieRole = new Cookie("role", roleCookie);
        resp.addCookie(cookieRole);
        resp.addCookie(cookieLogin);
        if (isFoundUser)
        {
            if (roleCookie.equals("user")){
                req.getRequestDispatcher("lab10views/userMainPage.jsp").forward(req, resp);
            }
            else {
                req.getRequestDispatcher("lab10views/mainPageAdmin.jsp").forward(req, resp);
            }
        }
        else if (isWrongPassword)
        {
            req.getRequestDispatcher("lab10views/signInForm.jsp").forward(req, resp);
        }
        else if (isNotFoundUser)
        {
            req.getRequestDispatcher("lab10views/signInForm.jsp").forward(req, resp);
        }
    }
}