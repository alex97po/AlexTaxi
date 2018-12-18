package com.pogorelov.util;

public final class Query {
    public static final String FIND_CLIENT_BY_ID = "SELECT * FROM client WHERE client.id = ?;";
    public static final String FIND_TAXI_BY_ID = "SELECT * FROM client WHERE client.id = ?;";
    public static final String INSERT_NEW_CLIENT_AUTH = "INSERT INTO client_auth (login, password) VALUES (?,?);";
    public static final String INSERT_NEW_CLIENT = INSERT_NEW_CLIENT_AUTH +
            "INSERT INTO client (name, client_auth_id) VALUES (?, SELECT id FROM client_auth WHERE login = ?);";
}
