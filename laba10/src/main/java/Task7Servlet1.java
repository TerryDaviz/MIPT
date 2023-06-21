import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Task7Servlet1", value = "/Task7-Servlet1")
public class Task7Servlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var num1  = 419;
        req.setAttribute("number1",num1);
        req.getRequestDispatcher("/Task7-Servlet2").include(req,resp);

        var num2 = req.getAttribute("number2");
        var writer = resp.getWriter();
        writer.println("<html><body>");
        writer.println("<h2>" + num2 + "</h2>");
        writer.println("</html></body>");
        writer.close();
    }
}
