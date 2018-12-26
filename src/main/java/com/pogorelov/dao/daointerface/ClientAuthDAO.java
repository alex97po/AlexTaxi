package com.pogorelov.dao.daointerface;

import com.pogorelov.entity.ClientAuth;

import java.util.Optional;

public interface ClientAuthDAO extends GenericDAO<ClientAuth, Long> {
    /**
     * Retrieve  entity client from database identified by LOGIN.
     *
     * @param login login of client
     * @return optional, which contains entity Client or null
     */
    Optional<ClientAuth> findOneByLogin(String login);


}
