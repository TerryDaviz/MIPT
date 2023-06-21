package Controller;

import Commands.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Controller", value = "/Controller")
public class Controller extends HttpServlet {
    public static enum Actions {
        command ,
        login,
        register,
        page,
        add_channel,
        del_channel,
        upd_channel,
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }
    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        String command = req.getParameter(Actions.command.toString());
        switch (command){
            case "login":{
                Welcome.WelcomeCommand(req,resp);
                break;
            }
            case "register":{
                Register.RegisterCommand(req,resp);
                break;
            }
            case "page": {
               Page.PageCommand(req,resp);
            }
            case "addChannel":{
                AddChannel.AddChannelCommand(req,resp);
            }
            case "delChannel":{
                DelChannel.DeleteChannelCommand(req,resp);
            }
            default:break;
        }
    }
}
