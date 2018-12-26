package com.pogorelov.dao.impl;

import com.pogorelov.dao.daointerface.ClientDAO;
import com.pogorelov.dao.factory.DataSourceFactory;
import com.pogorelov.entity.Client;
import com.pogorelov.entity.ClientAuth;
import com.pogorelov.util.Query;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySQLClientDAO implements ClientDAO {
    private static final Logger LOGGER = Logger.getLogger(MySQLClientDAO.class);
    private DataSourceFactory dataSourceFactory = DataSourceFactory.getInstance();
    private DataSource dataSource = dataSourceFactory.getDataSource();
    private MySQLClientAuthDAO mySQLClientAuthDAO = new MySQLClientAuthDAO();

    @Override
    public Optional<Client> findById(Long id) {
        try (PreparedStatement preparedStatement =
                     dataSource.getConnection().prepareStatement(Query.FIND_CLIENT_BY_ID)) {
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

    @Override
    public List<Client> findAll() {
        List<Client> result = new ArrayList<>();
        try (PreparedStatement preparedStatement =
                     dataSource.getConnection().prepareStatement(Query.FIND_ALL_CLIENT)) {
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
    public boolean insert(Client client) {
        try (PreparedStatement preparedStatement =
                     dataSource.getConnection().prepareStatement(Query.INSERT_NEW_CLIENT)) {
            preparedStatement.setString(1,client.getName());
            preparedStatement.setString(2,client.getClientAuth().getLogin());
        } catch (SQLException e) {
            LOGGER.error("Error connection to DB");
        }
        return false;
    }

    @Override
    public boolean update(Client client) {
        try (PreparedStatement preparedStatement =
                     dataSource.getConnection().prepareStatement(Query.UPDATE_CLIENT)) {
            preparedStatement.setString(1,client.getName());
            preparedStatement.setBigDecimal(2,client.getMoneySpent());
            preparedStatement.setLong(3,client.getId());
        } catch (SQLException e) {
            LOGGER.error("Error connection to DB");
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        try (PreparedStatement preparedStatement =
                     dataSource.getConnection().prepareStatement(Query.DELETE_CLIENT)) {
            preparedStatement.setLong(1,id);
        } catch (SQLException e) {
            LOGGER.error("Error connection to DB");
        }
        return false;
    }

    private Optional<Client> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        BigDecimal spentMoney = resultSet.getBigDecimal("spent_money");
        Long clientAuthId = resultSet.getLong("client_auth_id");
        ClientAuth clientAuth = mySQLClientAuthDAO.findById(clientAuthId).get();
        return Optional.of(new Client(id, name,spentMoney,clientAuth));
    }

}
