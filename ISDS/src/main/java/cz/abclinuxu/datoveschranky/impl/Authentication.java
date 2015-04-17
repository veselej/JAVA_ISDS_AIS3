package cz.abclinuxu.datoveschranky.impl;

import java.util.Map;
import java.util.logging.Logger;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;

import cz.abclinuxu.datoveschranky.common.impl.Config;

/**
 *
 * Autentizace
 * 
 */
public abstract class Authentication {

    protected final Config config;
    protected final Logger logger = Logger.getLogger(this.getClass().getName());

    protected Authentication(Config config) {
        this.config = config;
    }

    public <T> T createService(Service serviceBuilder, Class<T> serviceClass, String servicePostfix) {
        T service = serviceBuilder.getPort(serviceClass);
        configureService(((BindingProvider) service).getRequestContext(), servicePostfix);
        return service;
    }

    protected void configureService(Map<String, Object> requestContext, String servicePostfix) {
        requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, config.getServiceURL() + servicePostfix);
        this.configureServiceOverride(requestContext, servicePostfix);
    }

    protected abstract void configureServiceOverride(Map<String, Object> requestContext, String servicePostfix);
}
