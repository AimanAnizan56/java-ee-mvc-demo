package com.javaee.mvcdemo.controller;

import com.javaee.mvcdemo.DA.UserDA;
import com.javaee.mvcdemo.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Console;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "UserControllerServlet", value = "/UserControllerServlet")
public class UserControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost");

        String username = null, password = null, email = null;

        //get action
        String action = request.getParameter("action");

        switch (action.toLowerCase()) {
            case "login":
                username = request.getParameter("username");
                password = request.getParameter("password");

                // change later
                login(username, password);
            break;
            case "signup":
                username = request.getParameter("username");
                password = request.getParameter("password");
                email = request.getParameter("email");

                signup(username, password,email);
            break;
            default:

            break;
        }
    }

    // login function (trash code)
    private void login(String username, String password) {
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
    }

    private void signup(@NotNull String username, @NotNull @Min(6) String password,@Email String email) {
        // todo - get variable and add to database using model
        System.out.println("Sign Up method");

        // todo - check username if exist (add if)
        // UserDA.checkUsername(username);

        // add new user
        boolean isAdded = UserDA.addUser(new User(username, password, email));

        // todo -- successful message
        if(isAdded) System.out.println("New user added");
        else System.out.println("Cannot add user");

    }

    // used to check request parameter
    private void getAllParameterName(Enumeration <String> temp) {
        System.out.println("Request parameter name:-");
        while(temp.hasMoreElements()) {
            System.out.println("\t" + temp.nextElement());
        }
        System.out.println();
    }
}
