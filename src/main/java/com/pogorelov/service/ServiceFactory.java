package com.pogorelov.service;

/**
 * Factory for application services
 */
public class ServiceFactory {

    private ServiceFactory() {
    }

    private static class ServiceFactoryHolder {
        private static final ServiceFactory instance = new ServiceFactory();
    }

    public static ServiceFactory getInstance() {
        return ServiceFactoryHolder.instance;
    }
}