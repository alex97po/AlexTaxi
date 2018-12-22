package com.pogorelov.dao.impl;

import com.pogorelov.dao.daointerface.RideDAO;
import com.pogorelov.dao.factory.DataSourceFactory;
import com.pogorelov.entity.Client;
import com.pogorelov.entity.Ride;
import com.pogorelov.entity.Taxi;
import com.pogorelov.util.Query;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MySQLRideDAO implements RideDAO {
    private static final Logger LOGGER = Logger.getLogger(MySQLRideDAO.class);
    private DataSourceFactory dataSourceFactory = DataSourceFactory.getInstance();
    private DataSource dataSource = dataSourceFactory.getDataSource();
    private MySQLClientDAO mySQLClientDAO = new MySQLClientDAO();
    private MySQLTaxiDAO mySQLTaxiDAO = new MySQLTaxiDAO();

    @Override
    public Optional<Ride> findById(Long id) {
        try (PreparedStatement preparedStatement =
                     dataSource.getConnection().prepareStatement(Query.FIND_RIDE_BY_ID)) {
            preparedStatement.setLong(1,id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return getEntityFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error connection to DB");
        }
        return Optional.empty();
    }

    private Optional<Ride> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        BigDecimal cost = resultSet.getBigDecimal("cost");
        Double distance = resultSet.getDouble("distance");
        Long clientId = resultSet.getLong("client_id");
        Long taxiId =  resultSet.getLong("taxi_id");
        Client client = mySQLClientDAO.findById(clientId).get();
        Taxi taxi = mySQLTaxiDAO.findById(taxiId).get();
        return Optional.of(new Ride(id, cost, distance, client, taxi));
    }

    @Override
    public List<Ride> findAll() {
        return null;
    }

    @Override
    public boolean insert(Ride ride) {
        try (PreparedStatement preparedStatement =
                     dataSource.getConnection().prepareStatement(Query.INSERT_NEW_RIDE)) {
            preparedStatement.setBigDecimal(1,ride.getCost());
            preparedStatement.setDouble(2,ride.getDistance());
            preparedStatement.setLong(3,ride.getClient().getId());
            preparedStatement.setLong(4, ride.getTaxi().getId());
        } catch (SQLException e) {
            LOGGER.error("Error connection to DB");
        }
        return false;
    }

    @Override
    public boolean update(Ride ride) {
        try (PreparedStatement preparedStatement =
                     dataSource.getConnection().prepareStatement(Query.UPDATE_RIDE)) {
            preparedStatement.setBigDecimal(1,ride.getCost());
            preparedStatement.setDouble(2,ride.getDistance());
            preparedStatement.setLong(3,ride.getClient().getId());
            preparedStatement.setLong(4, ride.getTaxi().getId());
            preparedStatement.setLong(5, ride.getId());
        } catch (SQLException e) {
            LOGGER.error("Error connection to DB");
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        try (PreparedStatement preparedStatement =
                     dataSource.getConnection().prepareStatement(Query.DELETE_RIDE)) {
            preparedStatement.setLong(1,id);
        } catch (SQLException e) {
            LOGGER.error("Error connection to DB");
        }
        return false;
    }
}
