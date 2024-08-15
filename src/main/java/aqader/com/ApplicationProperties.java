package aqader.com;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.InputStream;


public enum ApplicationProperties {

    INSTANCE;
    final String propertiesFilePath = "config.properties";

    private final Properties properties;

    ApplicationProperties() {
        properties = new Properties();

        try (InputStream input = new FileInputStream(propertiesFilePath)) {
            properties.load(input);

        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

    }

    public String getLDAPHost() {
        return properties.getProperty("LDAP.HOST");
    }

    public Integer getLDAPPort() {
        return Integer.valueOf(properties.getProperty("LDAP.PORT"));
    }

    public String getLDAPBindUser() {
        return properties.getProperty("BIND.USR");
    }

    public String SearchFilter() {
        return properties.getProperty("SearchFilter");
    }

    public String outputFolder() {
        return properties.getProperty("Output");
    }

    public String getLDAPBindUserPassword() {
        return properties.getProperty("BIND.USER.PASSWORD");
    }

    public String getBaseDN() {
        return properties.getProperty("LDAP.BASE.DN");
    }
}
