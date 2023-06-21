import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ForwardToServlet",value = "/Forward-To-Servlet")
public class ForwardToServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            var toServletMsg = req.getAttribute("fromServletMsg");
            req.setAttribute("toServletMsg", toServletMsg);
            req.getRequestDispatcher("/Forward.jsp").forward(req, resp);
//            var writer = resp.getWriter();
//            writer.println("<html><body> henlo</body></html>");
        }
        catch (Exception ex){
            var writer = resp.getWriter();
            writer.println("<html><body>"+ex.getMessage() + "\ncause" + ex.getCause() + "</body></html>");
        }
    }
}
