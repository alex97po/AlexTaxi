package com.pogorelov.dao.daointerface;

import com.pogorelov.entity.ClientAuth;

public interface ClientAuthDAO extends GenericDAO<ClientAuth, Long> {

    ClientAuth getClientAuthenticate(String login, String password);
}
