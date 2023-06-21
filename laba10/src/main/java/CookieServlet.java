import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.Cookie;
import java.io.IOException;
@WebServlet(name="CookieServlet",value = "/Cookie-Servlet")
public class CookieServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var cookies = req.getCookies();
        String cookieRoleName = "role";
        String cookieDateName = "date";
        String cookieCountName = "count";
        Cookie cookieLogin = null;
        Cookie cookieRole = null;
        Cookie cookieDate = null;
        Cookie cookieCount = null;
        if (cookies != null) {
            for (var c : cookies) {
                var name = c.getName();
                switch (name) {
                    case "login":
                        cookieLogin = c;
                        break;
                    case "role":
                        cookieRole = c;
                        break;
                    case "date":
                        cookieDate = c;
                        break;
                    case "count":
                        cookieCount = c;
                        break;
                    default:
                        break;
                }
            }
        }
        var writer = resp.getWriter();
        try {
            writer.println("From cookies");
            writer.println("Login: " + cookieLogin.getValue());
            writer.println("Role: " + cookieRole.getValue());
        } catch (Exception ex){
            ex.printStackTrace();
            writer.close();
        }
        finally {
            writer.close();
        }
    }
}
