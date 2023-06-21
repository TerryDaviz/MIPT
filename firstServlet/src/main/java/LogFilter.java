import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LogFilter implements Filter {
    private FilterConfig config = null;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String remoteAdress = servletRequest.getRemoteAddr();
        String uri = ((HttpServletRequest)servletRequest).getRequestURI();
        String protocol = servletRequest.getProtocol();
        filterChain.doFilter(servletRequest,servletResponse);
        var writer = servletResponse.getWriter();
        writer.println("Log filter called");
        writer.println("==========");
        writer.println("User logged! User IP: " + remoteAdress +
                "Resource file: " + uri + "Protocol: " + protocol);
        writer.close();
    }
    @Override
    public void destroy() {
    }
}
