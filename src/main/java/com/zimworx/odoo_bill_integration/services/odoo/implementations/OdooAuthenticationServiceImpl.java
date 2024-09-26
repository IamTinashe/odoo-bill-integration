package com.zimworx.odoo_bill_integration.services.odoo.implementations;

import com.zimworx.odoo_bill_integration.OdooBillIntegrationApplication;
import com.zimworx.odoo_bill_integration.config.OdooProperties;
import com.zimworx.odoo_bill_integration.errorhandlers.OdooServiceException;
import com.zimworx.odoo_bill_integration.services.odoo.OdooAuthenticationService;
import com.zimworx.odoo_bill_integration.utils.OdooXmlRpcUtils;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyMap;

@Service
public class OdooAuthenticationServiceImpl implements OdooAuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(OdooAuthenticationServiceImpl.class);

    private final OdooProperties odooProperties;
    private final OdooXmlRpcUtils xmlRpcUtils;

    @Autowired
    public OdooAuthenticationServiceImpl(OdooProperties odooProperties, OdooXmlRpcUtils xmlRpcUtils) {
        this.odooProperties = odooProperties;
        this.xmlRpcUtils = xmlRpcUtils;
    }

    @Override
    public int authenticate() throws MalformedURLException, XmlRpcException {
        logger.info("Authenticating with Odoo");
        try {
            XmlRpcClient client = xmlRpcUtils.createClient(odooProperties.getUrl(), "/xmlrpc/2/common");
            int uid = (int) client.execute("authenticate", asList(
                    odooProperties.getDb(),
                    odooProperties.getUsername(),
                    odooProperties.getPassword(),
                    emptyMap()
            ));
            logger.info("Authenticated with Odoo. UID: {}", uid);
            return uid;
        } catch (XmlRpcException | MalformedURLException e) {
            logger.error("Failed to authenticate with Odoo: {}", e.getMessage(), e);
            throw new OdooServiceException("Failed to authenticate with Odoo", e);
        }
    }
}
