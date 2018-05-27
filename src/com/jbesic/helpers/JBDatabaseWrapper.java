package com.jbesic.helpers;

import java.sql.*;

public class JBDatabaseWrapper {
    //	Forbid class initialization by the application

    private JBDatabaseWrapper() {
    }

    public static Connection databaseConnection;

    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost/javasurveys?serverTimezone=UTC";
        String userName = "root";
        String password = "";

        if (!(JBDatabaseWrapper.databaseConnection instanceof Connection)) {
            try {
                JBDatabaseWrapper.databaseConnection = DriverManager.getConnection(url, userName, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return JBDatabaseWrapper.databaseConnection;
    }
}
