package com.pogorelov.util;

public final class Query {
    public static final String FIND_CLIENT_BY_ID = "SELECT * FROM client WHERE client.id = ?;";
    public static final String FIND_CLIENT_AUTH_BY_ID = "SELECT * FROM client_auth WHERE client.id = ?;";
    public static final String FIND_TAXI_BY_ID = "SELECT * FROM client WHERE client.id = ?;";

    public static final String INSERT_NEW_CLIENT_AUTH = "INSERT INTO client_auth (login, password) VALUES (?,?);";
    public static final String INSERT_NEW_CLIENT = "INSERT INTO client (name, client_auth_id) VALUES (?, SELECT id FROM client_auth WHERE login = ?);";
    public static final String INSERT_NEW_TAXI = "INSERT INTO taxi (car_type, state_number, driver_name) VALUES (?,?,?);";

    public static final String UPDATE_TAXI = "UPDATE taxi SET car_type = ?, state_number = ?, driver_name = ? WHERE id = ?;";
    public static final String UPDATE_CLIENT = "UPDATE client SET name = ?, moneySpent = ? WHERE id = ?;";
    public static final String UPDATE_CLIENT_AUTH = "UPDATE client_auth SET login = ?, password = ? WHERE id = ?;";

    public static final String DELETE_TAXI = "DELETE FROM taxi WHERE id = ?;";
    public static final String DELETE_CLIENT = "DELETE FROM client WHERE id = ?;";
    public static final String DELETE_CLIENT_AUTH = "DELETE FROM client_auth WHERE id = ?;";

}
