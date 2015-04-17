/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.abclinuxu.datoveschranky.impl;

import java.io.File;
import java.util.Map;

import javax.xml.ws.BindingProvider;

import cz.abclinuxu.datoveschranky.common.impl.Config;

/**
 *
 * @author xrosecky
 */
public class ClientCertAuthentication extends Authentication {

    protected File certFile;
    protected String certPassword;

    public ClientCertAuthentication(Config config, File certFile, String certPassword) {
        super(config);
        this.certFile = certFile;
        this.certPassword = certPassword;
    }

    @Override
    protected void configureServiceOverride(Map<String, Object> requestContext, String servicePostfix) {
    }

    @Override
    protected void configureService(Map<String, Object> requestContext, String servicePostfix) {
        requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, config.getServiceURLClientCert() + servicePostfix);
        this.configureServiceOverride(requestContext, servicePostfix);
    }


}
