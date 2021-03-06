package com.javaee.mvcdemo.connection;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Postgres {
    private static Connection conn = null;
    public Postgres() {
        try {
            // use Driver from postgresql
            Class.forName("org.postgresql.Driver");

            // DATABASE_URL=postgres://<username>:<password>@<host>:<port>/<dbname>
            URI dbURI;
            if(System.getenv("DATABASE_URL") != null) {
                // use cloud system environment
                dbURI = new URI(System.getenv("DATABASE_URL"));
                System.out.print("Remote postgres: ");
            } else {
                // local database postgres
                dbURI = new URI("postgres://postgres:system@localhost:5432/mvcdemo");
                System.out.print("Localhost postgres: ");
            }

            String username = dbURI.getUserInfo().split(":")[0]; // get db username
            String password = dbURI.getUserInfo().split(":")[1]; // get db password

            // jdbc:postgresql://<host>:<port>/<dbname>?user=<username>&password=<password>
            String dbURL = "jdbc:postgresql://" + dbURI.getHost() + ":" + dbURI.getPort() + dbURI.getPath();

            conn = DriverManager.getConnection(dbURL, username, password);
            System.out.println(conn != null ? " Connection establish!" : " Could not establish connection!");
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if(conn == null) new Postgres();
        return conn;
    }

    public static boolean closeConnection() {
        try {
            if(!conn.isClosed()) {
                conn.close(); conn = null;
                System.out.println("Connection closed");
            }
            return true;
        } catch (SQLException sqlErr) {
            sqlErr.printStackTrace(); return false;
        }
    }
}
