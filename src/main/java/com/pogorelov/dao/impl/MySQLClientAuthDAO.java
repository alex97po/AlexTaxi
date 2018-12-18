package com.pogorelov.dao.impl;

import com.pogorelov.dao.daointerface.ClientAuthDAO;
import com.pogorelov.dao.factory.DataSourceFactory;
import com.pogorelov.entity.ClientAuth;
import com.pogorelov.util.Query;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MySQLClientAuthDAO implements ClientAuthDAO {
    private static final Logger LOGGER = Logger.getLogger(MySQLClientDAO.class);
    private DataSourceFactory dataSourceFactory = DataSourceFactory.getInstance();
    private DataSource dataSource = dataSourceFactory.getDataSource();

    @Override
    public ClientAuth getClientAuthenticate(String login, String password) {

        return null;
    }

    @Override
    public Optional<ClientAuth> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<ClientAuth> findAll() {
        return null;
    }

    @Override
    public boolean insert(ClientAuth clientAuth) {
        try (PreparedStatement preparedStatement =
                     dataSource.getConnection().prepareStatement(Query.INSERT_NEW_CLIENT_AUTH)) {
            preparedStatement.setString(1,clientAuth.getLogin());
            preparedStatement.setString(2,clientAuth.getPassword());
        } catch (SQLException e) {
            LOGGER.error("Error connection to DB");
        }
        return false;
    }

    @Override
    public boolean update(ClientAuth clientAuth) {
        return false;
    }

    @Override
    public boolean delete(Long aLong) {
        return false;
    }
}
