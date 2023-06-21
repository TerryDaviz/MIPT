import data.Database;
import data.User;
import data.UserDAO;
import data.Utils;
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
           throw new ServletException("cannot retrieve areas", ex);
        }
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String encryptedPass = Utils.encryptPassword(password);

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
       var writer =  resp.getWriter();
       writer.println("<html><body>");
       for (var user : users){
           if (user.getUserName().equals(login) && user.getUserPassword().equals(encryptedPass)){
               isUserFound = true;
               writer.println("<h1>welcome</h1>");
               writer.println("<h2>name: " + user.getUserName() + "</h2>");
               writer.println("<h2>role: " + user.getUserRole() + "</h2>");
               writer.println("<h2>date: " + currentdate + "</h2>");
               loginCookie = user.getUserName();
               roleCookie = user.getUserRole();
               dateCookie = currentdate.toString();
               numberCookie = "1";
               setCookies(req,resp);
               if (!isUserFound){
                   req.getRequestDispatcher("Register.jsp").forward(req,resp);
               }
               writer.println("</body></html>");
               writer.close();
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
