import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

@WebServlet(name = "FirstServlet", value = "/First-Servlet")
public class FirstServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        var date = new Date();
        var printWriter = response.getWriter();
        printWriter.println("<html><body/>");
        printWriter.println("<h1>current date: " + date + "</h1>");
        printWriter.println("<div> <h2>protocol:" +  request.getProtocol()+ "</h2>");
        printWriter.println("<h2>Client's IP:" +  request.getRemoteAddr() +"</h2>");
        printWriter.println("<h2>Client's Name:" +  request.getRemoteHost() +"</h2>");
        printWriter.println("<h2>Access method:" +  request.getMethod() +"</h2>");
        printWriter.println("<h2>URL:" +  request.getRequestURL() +"</h2></div>");

        Enumeration<String> headers = request.getHeaderNames();
        String name = "";
        String value = "";
        while (headers.hasMoreElements()){
           name = headers.nextElement();
           value = request.getHeader(name);
           printWriter.println(name + ": " + value + "\n");
        }
        printWriter.println("</body></html>" );
        printWriter.close();
    }
    public void destroy(){
    }
}