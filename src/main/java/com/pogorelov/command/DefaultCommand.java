package com.pogorelov.command;

import com.pogorelov.exception.AppException;
import com.pogorelov.util.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Redirects to start index page (if command is null)
 */
public class DefaultCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        return Pages.INDEX_PAGE;
    }
}
