package com.pogorelov.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import com.pogorelov.exception.AppException;
import com.pogorelov.service.ServiceFactory;
import com.pogorelov.util.CommandNames;
import org.apache.log4j.Logger;

/**
 * Retrieves command from request and takes appropriate command from Map
 */
public class CommandFactory {
    private static final Logger LOGGER = Logger.getLogger(CommandFactory.class);
    private Map<String, Command> commandMap = new HashMap<>();
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private CommandFactory() {
        commandMap.put(CommandNames.INDEX_COMMAND, new DefaultCommand());
    }

    private static class CommandFactoryHolder {
        private static final CommandFactory instance = new CommandFactory();
    }

    public static CommandFactory getInstance() {
        return CommandFactoryHolder.instance;
    }

    public String invoke(HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
        String commandName = request.getParameter(CommandNames.PARAMETER_COMMAND);
        LOGGER.info(CommandNames.PARAMETER_COMMAND + commandName);
        Command command = commandMap.get(commandName);
        if (command == null) {
            command = new DefaultCommand();
        }
        try {
            return command.execute(request, response);
        } catch (AppException e) {
            return command.doOnError(request, e);
        }
    }

}

