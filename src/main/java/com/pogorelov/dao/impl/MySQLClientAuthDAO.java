package com.pogorelov.dao.impl;

import com.pogorelov.dao.daointerface.ClientAuthDAO;
import com.pogorelov.dao.factory.DataSourceFactory;
import com.pogorelov.entity.ClientAuth;
import com.pogorelov.util.Query;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySQLClientAuthDAO implements ClientAuthDAO {
    private static final Logger LOGGER = Logger.getLogger(MySQLClientAuthDAO.class);
    private DataSourceFactory dataSourceFactory = DataSourceFactory.getInstance();
    private DataSource dataSource = dataSourceFactory.getDataSource();

    public MySQLClientAuthDAO () {
    }

    public MySQLClientAuthDAO (Connection connection) {
    }

    @Override
    public Optional<ClientAuth> findOneByLogin(String login) {
        try (PreparedStatement preparedStatement =
                     dataSource.getConnection().prepareStatement(Query.FIND_CLIENT_AUTH_BY_LOGIN)) {
            preparedStatement.setString(1, login);
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

    private static class MySQLClientAuthDAOHolder {
        private static MySQLClientAuthDAO instance(Connection connection) {
            return new MySQLClientAuthDAO(connection);
        }
    }

    public static MySQLClientAuthDAO getInstance(Connection connection) {
        return MySQLClientAuthDAOHolder.instance(connection);
    }

    @Override
    public Optional<ClientAuth> findById(Long id) {
        try (PreparedStatement preparedStatement =
                     dataSource.getConnection().prepareStatement(Query.FIND_CLIENT_AUTH_BY_ID)) {
            preparedStatement.setLong(1, id);
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

    private Optional<ClientAuth> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        return Optional.of(new ClientAuth(id, login, password));
    }

    @Override
    public List<ClientAuth> findAll() {
        List<ClientAuth> result = new ArrayList<>();
        try (PreparedStatement preparedStatement =
                     dataSource.getConnection().prepareStatement(Query.FIND_ALL_CLIENT_AUTH)) {
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
    public boolean insert(ClientAuth clientAuth) {
        try (PreparedStatement preparedStatement =
                     dataSource.getConnection().prepareStatement(Query.INSERT_NEW_CLIENT_AUTH)) {
            preparedStatement.setString(1, clientAuth.getLogin());
            preparedStatement.setString(2, clientAuth.getPassword());
        } catch (SQLException e) {
            LOGGER.error("Error connection to DB");
        }
        return false;
    }

    @Override
    public boolean update(ClientAuth clientAuth) {
        try (PreparedStatement preparedStatement =
                     dataSource.getConnection().prepareStatement(Query.UPDATE_CLIENT_AUTH)) {
            preparedStatement.setString(1, clientAuth.getLogin());
            preparedStatement.setString(2, clientAuth.getPassword());
            preparedStatement.setLong(3, clientAuth.getId());
        } catch (SQLException e) {
            LOGGER.error("Error connection to DB");
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        try (PreparedStatement preparedStatement =
                     dataSource.getConnection().prepareStatement(Query.DELETE_CLIENT_AUTH)) {
            preparedStatement.setLong(1, id);
        } catch (SQLException e) {
            LOGGER.error("Error connection to DB");
        }
        return false;
    }


}
