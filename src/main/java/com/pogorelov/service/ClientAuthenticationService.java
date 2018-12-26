package com.pogorelov.service;
import com.pogorelov.dao.daointerface.ClientAuthDAO;
import com.pogorelov.dao.factory.DAOFactory;
import com.pogorelov.dao.factory.DataSourceFactory;
import com.pogorelov.entity.ClientAuth;
import com.pogorelov.exception.DAOException;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Optional;
import javax.sql.DataSource;

/**
 * Service for interact with DAO layer interface UserAuthenticationDao
 */
public class ClientAuthenticationService {
    private ClientAuthenticationService() {
    }

    private static class ClientAuthenticationServiceHolder {
        private static final ClientAuthenticationService instance = new ClientAuthenticationService();
    }

    public static ClientAuthenticationService getInstance() {
        return ClientAuthenticationServiceHolder.instance;
    }

    /**
     * Retrieve entity client from database identified by LOGIN.
     *
     * @param clientAuthFromRequest identifier of client
     * @return optional, which contains entity Client or null
     */
    public Optional<ClientAuth> authentication(ClientAuth clientAuthFromRequest) throws DAOException {
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        Optional<ClientAuth> value;
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DataSourceFactory daoFactory = DataSourceFactory.getInstance();
            daoFactory.getDataSource().getConnection();
            ClientAuthDAO clientAuthDAO = daoFactory.createClientAuthDAO;
            value = ClientAuthDAO.findOneByLogin(clientAuthFromRequest.getLogin());
            connection.commit();
            if (!value.isPresent()) {
                connection.rollback();
                throw new DAOException("Wrong login");
            }
            if (!value.get().getPassword().equals(clientAuthFromRequest.getPassword())) {
                connection.rollback();
                throw new DAOException("Wrong password");
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
        return value;
    }

    /**
     * Retrieve id from userAuthentication table
     *
     * @param login login of user
     * @return Long id
     */
    public Long getId(String login) {
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        Optional<Long> value;
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DAOFactory daoFactory = DAOFactory.getDaoFactory(connection);
            ClientAuthDAO clientAuthDAO = daoFactory.createClientAuthDAO();
            value = clientAuthDAO.findId (login);
            if (!value.isPresent()) {
                connection.rollback();
                throw new DAOException("ID not found");
            }
            connection.commit();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
        return value.get();
    }
}