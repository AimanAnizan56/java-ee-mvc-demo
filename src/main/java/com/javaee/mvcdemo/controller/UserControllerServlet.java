package com.javaee.mvcdemo.controller;

import com.javaee.mvcdemo.DA.UserDA;
import com.javaee.mvcdemo.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "user", value = "/user/*")
public class UserControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost");

        // String action = request.getParameter("action"); //get action
        String action = request.getPathInfo();
        String username = null, password = null, email = null;

        switch (action.toLowerCase()) {
            case "/login":
                username = request.getParameter("username");
                password = request.getParameter("password");

                // todo - change later
                login(username, password);
            break;
            case "/signup":
                username = request.getParameter("username");
                password = request.getParameter("password");
                email = request.getParameter("email");

                if (signup(username, password,email)) {
                    // todo - if true, redirect to login page (index.jsp)
                    request.setAttribute("message","Register successful, you can now login!");
                    System.out.println("New user added");
                    request.getRequestDispatcher("/index.jsp").forward(request,response);
                } else {
                    // todo - false, message try again! (assume that user put existed username)
                }
            break;
            default:
                // none (or have something later)
            break;
        }
    }

    // LOGIN METHOD - (trash code)
    private void login(String username, String password) {
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
    }

    // SIGN UP METHOD
    private boolean signup(@NotNull String username, @NotNull @Min(6) String password,@Email String email) {
        // return false for error message
        if (UserDA.checkUsernameExist(username)) return false; // terminate if the username is existed

        // add new user - successful (redirect to index.jsp -- login page)
        return UserDA.addUser(new User(username, password, email));
    }

    // USED TO CHECK PARAMETER NAMES FROM REQUEST
    private void getAllParameterName(Enumeration <String> temp) {
        System.out.println("Request parameter name:-");
        while(temp.hasMoreElements()) System.out.println("\t" + temp.nextElement());
        System.out.println();
    }
}
