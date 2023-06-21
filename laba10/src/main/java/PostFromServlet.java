import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "PostFromServlet", value = "/Post-From-Servlet" )
public class PostFromServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       resp.setContentType("text/html");
       resp.setCharacterEncoding("UTF-8");
       var writer = resp.getWriter();
       writer.append("<!DOCTYPE html>\r\n")
               .append("<html>\r\n")
               .append("        <head>\r\n")
               .append("            <title>Form input</title>\r\n")
               .append("        </head>\r\n")
               .append("        <body>\r\n")
               .append("            <form action=\"Post-To-Servlet\" method=\"POST\">\r\n")
               .append("                Enter your name: \r\n")
               .append("                <input type=\"text\" name=\"user\" />\r\n")
               .append("                <input type=\"submit\" value=\"Submit\" />\r\n")
               .append("            </form>\r\n")
               .append("        </body>\r\n")
               .append("</html>\r\n");
    }
}
