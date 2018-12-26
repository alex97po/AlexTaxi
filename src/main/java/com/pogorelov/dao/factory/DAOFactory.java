package com.pogorelov.dao.factory;

import com.pogorelov.dao.daointerface.ClientAuthDAO;
import com.pogorelov.dao.daointerface.ClientDAO;
import com.pogorelov.dao.daointerface.RideDAO;
import com.pogorelov.dao.daointerface.TaxiDAO;
import com.pogorelov.entity.ClientAuth;
import com.pogorelov.entity.Ride;
import com.pogorelov.entity.Taxi;

import java.sql.Connection;

/**
 * Abstract factory for application DAO
 */
public abstract class DAOFactory {
    public static DAOFactory getDaoFactory(Connection connection) {
        return MySqlDaoFactory.getInstance(connection);
    }

    public abstract ClientAuthDAO createClientAuthDAO ();
    public abstract ClientDAO createClientDAO ();
    public abstract RideDAO createRideDAO ();
    public abstract TaxiDAO createTaxiDAO ();
}
