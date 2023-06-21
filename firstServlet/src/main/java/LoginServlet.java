import data.Database;
import data.User;
import data.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name="LoginServlet",value ="/Login-Servlet")
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO;
    public List<User> users = new ArrayList<User>();
    private int number;
    private String loginCookie = "testlogin";
    private String roleCookie = "testrole";
    private String numberCookie;
    private String dateCookie;
    public void init() throws ServletException {
       String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
       String url = "jdbc:sqlserver://localhost:1433;database=java_lab9_Users;encrypt=false;user=sa;password=11";
       var database = new Database(driver,url,"sa","11");
       this.userDAO = new UserDAO(database);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Date currentdate = new Date();
        boolean isUserFound = false;
        number++;
        try {
            users = userDAO.getUsersList();
        }
        catch (SQLException ex){
           throw new ServletException("cannot retrieve table", ex);
        }
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        for (var user : users){
           if (user.getUserName().equals(login) && user.getPassword().equals(password)){
               isUserFound = true;
               req.setAttribute("name",user.getUserName());
               req.setAttribute("role",user.getRole());
               req.setAttribute("date",currentdate);
               loginCookie = user.getUserName();
               roleCookie = user.getRole();
               dateCookie = currentdate.toString();
               numberCookie = "1";
               setCookies(req,resp);
               if (!isUserFound){
                   req.getRequestDispatcher("/registration.jsp").forward(req,resp);
               }
               req.getRequestDispatcher("/Main.jsp").forward(req,resp);
           }
        }
    }
    private void setCookies(HttpServletRequest req ,HttpServletResponse resp){
        var session = req.getSession();
        var cookieLogin = new Cookie("login", loginCookie);
        var cookieCount = new Cookie("count","1");
        var cookieRole = new Cookie("role",roleCookie);
        var cookieDate = new Cookie("date", new Date(session.getLastAccessedTime()).toString());
        resp.addCookie(cookieRole);
        resp.addCookie(cookieLogin);
//        resp.addCookie(cookieDate);
//        resp.addCookie(cookieCount);
    }
}
