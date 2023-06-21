package Commands;

import DB.DBConnection;
import Model.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class Welcome {
    public static void WelcomeCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var dbContext = new DBConnection();
        var login = req.getParameter("login");
        var password = req.getParameter("password");
        if (login.equals("") || password.equals("")) {
            req.setAttribute("result","Fill all the fields");
            var reqDispatcher = req.getRequestDispatcher("/welcome.jsp");
            reqDispatcher.forward(req,resp);
        }
        String role = null;
        Users user  = null;

        try{
            user = dbContext.GetByUsername(login);
        } catch (SQLException ex){
            req.setAttribute("result","db error");
        }
        if (user != null){
            role = user.getRole();
            if (!user.getPassword().equals(password)){
                req.setAttribute("result","wrong password");
                req.getRequestDispatcher("/welcome.jsp").forward(req,resp);
            }
            else if (role.equals("user") || role.equals("admin")){
                req.getRequestDispatcher("/controller?command=page").forward(req,resp);
            }
            else {
                req.getRequestDispatcher("/register.jsp").forward(req,resp);
            }
        }
    }
}
