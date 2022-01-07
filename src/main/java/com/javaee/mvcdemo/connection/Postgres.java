package com.javaee.mvcdemo.connection;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Postgres {
    private static Connection conn = null;
    public Postgres() {
        try {
            // DATABASE_URL=postgres://<username>:<password>@<host>:<port>/<dbname>
            URI dbURI;
            if(System.getenv("DATABASE_URL") != null) {
                // use cloud system environment
                dbURI = new URI(System.getenv("DATABASE_URL"));
            } else {
                // local database postgres
                dbURI = new URI("postgres://postgres:system>@localhost:5432/mvcdemo");
            }

            String username = dbURI.getUserInfo().split(":")[0]; // get db username
            String password = dbURI.getUserInfo().split(":")[1]; // get db password

            // jdbc:postgresql://<host>:<port>/<dbname>?user=<username>&password=<password>
            String dbURL = "jdbc:postgresql://" + dbURI.getHost() + ":" + dbURI.getPort() + dbURI.getPath();

            conn = DriverManager.getConnection(dbURL, username, password);
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if(conn == null) {
            new Postgres();
        }
        return conn;
    }

    public static void closeConnection() {
        try {
            if(!conn.isClosed()) {
                conn.close();
                conn = null;
            }
        } catch (SQLException sqlErr) {
            sqlErr.printStackTrace();
        }
    }
}
