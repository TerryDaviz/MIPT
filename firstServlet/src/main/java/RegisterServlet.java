import com.microsoft.sqlserver.jdbc.StringUtils;
import data.Database;
import data.User;
import data.UserDAO;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.Provider;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@WebServlet(name = "RegisterServlet", value = "/Register-Servlet")
public class RegisterServlet extends HttpServlet {
    private UserDAO userDAO;
    public List<User> userList= new ArrayList<>();
//    private static Logger logger = Logger.getLogger(SignInServlet.class);
    @Override
    public void init(ServletConfig config) throws ServletException {
        var driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        var url = "jdbc:sqlserver://localhost:1433;database=Java_lab9_Users;encrypt=false;user=sa;password=11";
        var username = "sa";
        var password = "11";
        var database = new Database(driver, url, username, password);
        this.userDAO = new UserDAO(database);
//        BasicConfigurator.configure();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var login = req.getParameter("login");
        var password = req.getParameter("password");
        try{
            userDAO.addUser(login,password);
        }catch (SQLException ex){
            ex.printStackTrace();
            var writer = resp.getWriter();
            writer.println("<html><body>");
            writer.println("<h2>Unable to add user</h2>");
            writer.println("</body></html>");
            writer.close();
        }
        var writer = resp.getWriter();
        writer.println("<html><body>");
        writer.println("<h2>Registered successfully");
        writer.println("</body></html>");
        writer.close();
    }

    //    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Date currentDate = new Date();
//        boolean isUserExist = false;
//        boolean isInvalidPassword = false;
//        String loginCookie  = "testLogin";
//        String roleCookie = "testRole";
//        try {
//            userList = userDAO.getUsersList();
//        }
//        catch (SQLException ex){
//            throw new ServletException("Cannot retrieve user list", ex);
//        }
//        String login = req.getParameter("login");
//        String password = req.getParameter("password");
//        for (var user : userList){
//            if (user.getUserName().equals(login)){
//                isUserExist = true;
//                req.setAttribute("ServletResponse","This login is already in use");
//                break;
//            }
//            else if (password.length() < 3){
//                isInvalidPassword = true;
//                req.setAttribute("ServletResponse","Password is too short");
//            }
//            else {
//                try {
//                    userDAO.addUser(login,password);
////                    req.setAttribute("");
//                } catch (SQLException ex) {
//                   ex.printStackTrace();
//                   req.setAttribute("ServletResponse","This login is already in use");
//                }
//                break;
//            }
//        }
//
//        if (StringUtils.isEmpty(login)||StringUtils.isEmpty(password)){
//            req.setAttribute("ServletResponse","Please fill all fields!");
//            req.getRequestDispatcher("/singUpForm_10.jsp").forward(req,resp);
//        }
//        if (isInvalidPassword){
//            req.getRequestDispatcher("/singUpForm_10.jsp").forward(req,resp);
//        }
//        else if (isUserExist){
//            req.getRequestDispatcher("/singUpForm_10.jsp").forward(req,resp);
//        }
//        else {
//            req.getRequestDispatcher("/singInForm_10.jsp").forward(req,resp);
//        }
//    }
}
