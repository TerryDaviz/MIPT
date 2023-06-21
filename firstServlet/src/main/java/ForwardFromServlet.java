import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name ="ForwardFromServlet", value = "/Forward-From-Servlet")
public class ForwardFromServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var fromServletMsg = "Message from 'from' servlet";
        try {
            req.setAttribute("fromServletMsg", fromServletMsg);
            req.getRequestDispatcher("/Forward-To-Servlet").forward(req, resp);
        }
        catch (Exception ex){
            var writer = resp.getWriter();
            writer.println("<html><body>"+ex.getMessage() + "\ncause" + ex.getCause() + "</body></html>");
        }
    }
}
