package com.sealinetech.until;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseHelper {
    public Connection getConnection(){
        Connection connection=null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mydatabase",
                    "user","user");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}
