package com.pogorelov.dao.impl;

import com.pogorelov.dao.daointerface.TaxiDAO;
import com.pogorelov.dao.factory.DataSourceFactory;
import com.pogorelov.entity.Taxi;
import com.pogorelov.util.Query;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySQLTaxiDAO implements TaxiDAO {
    private static final Logger LOGGER = Logger.getLogger(MySQLTaxiDAO.class);
    private DataSourceFactory dataSourceFactory = DataSourceFactory.getInstance();
    private DataSource dataSource = dataSourceFactory.getDataSource();

    @Override
    public Optional<Taxi> findById(Long id) {
        try (PreparedStatement preparedStatement =
                     dataSource.getConnection().prepareStatement(Query.FIND_TAXI_BY_ID)) {
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

    private Optional<Taxi> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String carType = resultSet.getString("car_type");
        String stateNumber = resultSet.getString("state_number");
        String driverName = resultSet.getString("driver_name");
        return Optional.of(new Taxi(id, carType, stateNumber, driverName));
    }

    @Override
    public List<Taxi> findAll() {
        List<Taxi> result = new ArrayList<>();
        try (PreparedStatement preparedStatement =
                     dataSource.getConnection().prepareStatement(Query.FIND_ALL_TAXI)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    if (getEntityFromResultSet(resultSet).isPresent()) {
                        result.add(getEntityFromResultSet(resultSet).get());
                }
                    LOGGER.info("Table of Taxi entities created");
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error connection to DB");
        }
        return result;
    }

    @Override
    public boolean insert(Taxi taxi) {
        try (PreparedStatement preparedStatement =
                     dataSource.getConnection().prepareStatement(Query.INSERT_NEW_TAXI)) {
            preparedStatement.setString(1,taxi.getCarType());
            preparedStatement.setString(2,taxi.getStateNumber());
            preparedStatement.setString(3,taxi.getDriverName());
        } catch (SQLException e) {
            LOGGER.error("Error connection to DB");
        }
        return false;
    }

    @Override
    public boolean update(Taxi taxi) {
        try (PreparedStatement preparedStatement =
                     dataSource.getConnection().prepareStatement(Query.UPDATE_TAXI)) {
            preparedStatement.setString(1,taxi.getCarType());
            preparedStatement.setString(2,taxi.getStateNumber());
            preparedStatement.setString(3,taxi.getDriverName());
            preparedStatement.setLong(4, taxi.getId());
        } catch (SQLException e) {
            LOGGER.error("Error connection to DB");
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        try (PreparedStatement preparedStatement =
                     dataSource.getConnection().prepareStatement(Query.DELETE_TAXI)) {
            preparedStatement.setLong(1,id);
        } catch (SQLException e) {
            LOGGER.error("Error connection to DB");
        }
        return false;
    }
}
