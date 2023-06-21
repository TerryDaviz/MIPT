package Commands;

import DB.DBConnection;
import Model.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class Register {
    public static void RegisterCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        var login = req.getParameter("login");
        var password  = req.getParameter("password");
        Users user = null;
        if (login.isEmpty() || password.isEmpty()){
            req.setAttribute("result","Fill all fields");
            req.getRequestDispatcher("/register.jsp").forward(req,resp);
        }
        else {
            try{
                var dbContext = new DBConnection();
                user = dbContext.GetByUsername(login);
                if (user != null){
                    req.setAttribute("result","User with the same login already exists");
                    req.getRequestDispatcher("/register.jsp").forward(req,resp);
                    return;
                }
                else {
                    var newUser = new Users(login,password,"user");
                    dbContext.AddUser(newUser);
                    return;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        req.getRequestDispatcher("/welcome.jsp").forward(req,resp);
    }
}
