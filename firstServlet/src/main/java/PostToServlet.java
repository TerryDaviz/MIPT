import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "PostToServlet", value = "/Post-To-Servlet")
public class PostToServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var user = req.getParameter("user");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        var writer = resp.getWriter();
        writer.append("<!DOCTYPE html>\r\n")
                .append("<html>\r\n")
                .append("        <head>\r\n")
                .append("            <title>Welcome message</title>\r\n")
                .append("        </head>\r\n")
                .append("        <body>\r\n");
        if (user != null && !user.trim().isEmpty()) {
            writer.append("    Welcome, " + user + "!\r\n");
        } else {
            writer.append("    You did not entered a name!\r\n");
        }
        writer.append("        </body>\r\n")
                .append("</html>\r\n");
    }
}
