package com.pogorelov.util;

public final class Query {
    public static final String FIND_CLIENT_BY_ID = "SELECT * FROM client WHERE client.id = ?";

    public static final String FIND_CLIENT_BY_NAME = "SELECT * FROM client WHERE client.name = ? and client.id = ?";
}
