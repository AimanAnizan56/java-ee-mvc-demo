package com.javaee.mvcdemo.DA;

import com.javaee.mvcdemo.connection.Postgres;
import com.javaee.mvcdemo.model.User;

import javax.validation.constraints.NotNull;
import java.sql.*;

public abstract class UserDA {

    // Add new user
    public static boolean addUser(User user) {
        String sql = "INSERT INTO users (username,password,email) VALUES (?,?,?)";
        try {
            PreparedStatement prepStmt = Postgres.getConnection().prepareStatement(sql); // get connection and prepare statement

            // set string parameter
            prepStmt.setString(1, user.getUsername());
            prepStmt.setString(2, user.getPassword());
            prepStmt.setString(3, user.getEmail());

            return prepStmt.executeUpdate() == 1 && Postgres.closeConnection(); // return true if row added (1 means 1 row added)
        } catch (SQLException err) {
            err.printStackTrace();
            Postgres.closeConnection();
            return false;
        }
    }

    // check username if exist in database
    public static boolean checkUsernameExist(@NotNull  String username) {
        String sql = "SELECT ID FROM users WHERE username=?";
        boolean result = false;

        try {
            PreparedStatement prepStmt = Postgres.getConnection().prepareStatement(sql);
            prepStmt.setString(1, username);

            result = prepStmt.executeQuery().next();
        } catch(SQLException err) {
            err.printStackTrace();
        }

        Postgres.closeConnection();
        return result;
    }
}
