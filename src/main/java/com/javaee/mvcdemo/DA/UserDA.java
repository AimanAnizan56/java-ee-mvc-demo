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
            Connection conn = Postgres.getConnection(); // get connection

            PreparedStatement prepStmt = conn.prepareStatement(sql); // prepare statement

            // set string parameter
            prepStmt.setString(1, user.getUsername());
            prepStmt.setString(2, user.getPassword());
            prepStmt.setString(3, user.getEmail());

            return prepStmt.executeUpdate() == 1; // return true if row added (1 means 1 row added)
        } catch (SQLException err) {
            err.printStackTrace();
            return false;
        }
    }

    // check username if exist in database
    public static boolean checkUsername(@NotNull  String username) {
        // todo - add sql to check later
        return true;
    }
}
