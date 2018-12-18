package com.pogorelov.dao.impl;

import com.pogorelov.dao.daointerface.ClientDAO;
import com.pogorelov.dao.factory.DataSourceFactory;
import com.pogorelov.entity.Client;
import com.pogorelov.entity.ClientAuth;
import com.pogorelov.util.Query;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        return null;
    }

    @Override
    public boolean insert(Client client) {
        return false;
    }

    @Override
    public boolean update(Client client) {
        return false;
    }

    @Override
    public boolean delete(Long aLong) {
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
