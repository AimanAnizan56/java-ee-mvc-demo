package com.javaee.mvcdemo.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.Console;
import java.io.IOException;

@WebServlet(name = "UserControllerServlet", value = "/UserControllerServlet")
public class UserControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost");

        //get action
        String action = request.getParameter("action");

        switch (action.toLowerCase()) {
            case "login":
                String username = request.getParameter("username");
                String password = request.getParameter("password");

                login(username, password);
            break;
            default:

            break;
        }
    }

    private void login(String username, String password) {
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
    }
}
