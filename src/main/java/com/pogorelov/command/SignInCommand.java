package com.pogorelov.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pogorelov.entity.ClientAuth;
import com.pogorelov.exception.AppException;
import com.pogorelov.service.ClientAuthenticationService;
import com.pogorelov.util.Pages;
import com.pogorelov.util.Parameters;
import org.apache.log4j.Logger;

/**
 * It checks user's credentials and lets him to sign in
 */
public class SignInCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(SignInCommand.class);
    private ClientAuthenticationService clientAuthenticationService;
    private ClientService clientService;

    public SignInCommand(ClientAuthenticationService clientAuthenticationService, ClientService clientService) {
        this.clientAuthenticationService = clientAuthenticationService;
        this.clientService = clientService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        String login = request.getParameter(Parameters.LOGIN);
        String password = request.getParameter(Parameters.PASSWORD);
        ClientAuth clientAuth = new ClientAuth(login, password);
        request.getSession().setAttribute(Parameters.CLIENT_AUTH, clientAuth);
        clientAuthenticationService.authentication(clientAuth);
        LOGGER.info(clientService.findClientByLogin(login));
        return Pages.INDEX_PAGE;
    }

    @Override
    public String doOnError(HttpServletRequest request, Exception e) throws AppException {
        LOGGER.error(e.getMessage());
        request.setAttribute(Parameters.EXCEPTION, e.getMessage());
        return Pages.SIGN_IN_PAGE;
    }

}