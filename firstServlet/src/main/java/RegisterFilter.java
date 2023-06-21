import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.ArrayList;

@WebFilter("/ResiterFilter")
public class RegisterFilter implements Filter {
  private FilterConfig config;
  private static ArrayList<String> pages;
  public RegisterFilter(){
      if (pages == null){
          pages = new ArrayList<String>();
      }
  }
    @Override
    public void destroy() {
      config = null;
    }
//    @see Filter#init(config);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
      config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (config.getInitParameter("active").equalsIgnoreCase("true")){
            HttpServletRequest req = (HttpServletRequest)servletRequest;
            String [] list = req.getRequestURI().split("/");
            String page = null;
            if (list[list.length -1].indexOf(".jsp") > 0){
                page = list[list.length-1];
            }
            if ((page!=null) && page.equalsIgnoreCase("Main.jsp")){
               if (pages.contains("Login.jsp")) {
                   filterChain.doFilter(servletRequest,servletResponse);
                   return;
               }else {
                   var context = config.getServletContext();
                   var dispatcher = context.getRequestDispatcher("/Login.jsp");
                   dispatcher.forward(servletRequest,servletResponse);
                   return;
               }
            }
            else if (page != null){
                if (!pages.contains(page)){
                    pages.add(page);
                }
            }
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }
}

