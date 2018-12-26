package com.pogorelov.dao.factory;

import com.pogorelov.dao.daointerface.ClientAuthDAO;
import com.pogorelov.dao.daointerface.ClientDAO;
import com.pogorelov.dao.daointerface.RideDAO;
import com.pogorelov.dao.daointerface.TaxiDAO;
import com.pogorelov.dao.impl.MySQLClientAuthDAO;

import java.sql.Connection;

/**
 * MySql implementation for DaoFactory
 */
public class MySqlDaoFactory extends DAOFactory {
    private Connection connection;

    private MySqlDaoFactory(Connection connection) {
        this.connection = connection;
    }

    private static class MySqlDaoFactoryHolder {
        private static MySqlDaoFactory instance(Connection connection) {
            return new MySqlDaoFactory(connection);
        }
    }

    public static MySqlDaoFactory getInstance(Connection connection) {
        return MySqlDaoFactoryHolder.instance(connection);
    }

    @Override
    public ClientAuthDAO createClientAuthDAO () {
        return MySQLClientAuthDAO.getInstance(connection);
    }

    @Override
    public ClientDAO createClientDAO () {

    }

    @Override
    public RideDAO createRideDAO () {

    }

    @Override
    public TaxiDAO createTaxiDAO () {

    }
}
