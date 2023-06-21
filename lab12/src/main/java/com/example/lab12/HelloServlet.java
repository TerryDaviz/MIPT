package com.example.lab12;

import java.io.*;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("item1");
        strings.add("item2");
        strings.add("item3");
        req.setAttribute("items",strings);
        getServletContext().getRequestDispatcher("/userTags.jsp").forward(req,resp);
    }
}