package com.javaee.mvcdemo.DA;

import com.javaee.mvcdemo.connection.Postgres;
import com.javaee.mvcdemo.model.User;

import javax.validation.constraints.NotNull;
import java.sql.*;

public abstract class UserDA {

    // Add new user
    public static void addUser(User user) {
        String sql = "INSERT INTO User (username,password,email) VALUES (?,?,?)";
        try {
            Connection conn = Postgres.getConnection(); // get connection

            PreparedStatement prepStmt = conn.prepareStatement(sql); // prepare statement

            // set string parameter
            prepStmt.setString(1, user.getUsername());

            // todo - ResultSet rs = prepStmt.executeQuery();
        } catch (SQLException err) {
            err.printStackTrace();
        }

    }

    // check username if exist in database
    public static boolean checkUsername(@NotNull  String username) {
        // todo - add sql to check later
        return true;
    }
}
