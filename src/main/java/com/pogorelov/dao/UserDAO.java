package com.pogorelov.dao;

import com.pogorelov.ConnectionDB;
import com.pogorelov.model.User;
import java.sql.SQLException;

public class UserDAO {

    private static String connectUsersTable = "SELECT * FROM users";


    public User getUserByLoginPassword(final String login, final String password) throws SQLException {

        ConnectionDB.connectionDB(connectUsersTable);


        User result = new User();


        return result;
    }

}
