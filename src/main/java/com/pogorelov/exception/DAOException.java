package com.pogorelov.exception;

/**
 * Base Dao exception
 */
public class DAOException extends AppException {
    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable source) {
        super(message, source);
    }
}