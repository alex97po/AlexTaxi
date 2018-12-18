package com.pogorelov.dao.impl;

import com.pogorelov.dao.daointerface.ClientAuthDAO;
import com.pogorelov.dao.factory.DataSourceFactory;
import com.pogorelov.entity.ClientAuth;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class MySQLClientAuthDAO implements ClientAuthDAO {
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
