package com.pogorelov;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;

public class ConnectionDB {
    private static final  String URL = "jdbc:mysql://localhost:3306/alextaxi";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void connectionDB (String stat) throws SQLException {
        Connection connection;
        Driver driver = new FabricMySQLDriver();
        DriverManager.registerDriver(driver);
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Statement statement = connection.createStatement();
        statement.execute (stat);
    }

}
