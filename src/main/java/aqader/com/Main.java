package aqader.com;

import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.SearchRequest;
import com.unboundid.ldap.sdk.SearchResult;
import com.unboundid.ldap.sdk.SearchScope;
//import com.unboundid.util.json.JSONException;
//import com.unboundid.util.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
//import java.util.Properties;
//import java.util.logging.Logger;
//import java.util.logging.Level;

public class Main {
    public static void main(String[] args) throws LDAPException {

        final String host = ApplicationProperties.INSTANCE.getLDAPHost();
        final int port = ApplicationProperties.INSTANCE.getLDAPPort();
        final String user = ApplicationProperties.INSTANCE.getLDAPBindUser();
        final String password = ApplicationProperties.INSTANCE.getLDAPBindUserPassword();
        final String baseDN = ApplicationProperties.INSTANCE.getBaseDN();
//        final String searchFilter = "(&(objectClass=user)(objectCategory=person))";
        final String searchFilter = ApplicationProperties.INSTANCE.SearchFilter();
        final String outputFile = ApplicationProperties.INSTANCE.outputFolder();


        try (LDAPConnection connection = new LDAPConnection(host, port, user, password)){
            // Specify the search scope and attributes to return (null for all attributes)
            SearchRequest searchRequest = new SearchRequest(baseDN, SearchScope.SUB, searchFilter, "cn");
            SearchResult searchResult = connection.search(searchRequest);

            // Process the search result
            StringBuilder resultBuilder = new StringBuilder();
            searchResult.getSearchEntries().forEach(entry -> {
                resultBuilder.append(entry.getAttributeValue("cn")).append("\n");
            });

            // Defining the file name of the file
            Path fileName = Path.of(outputFile);

            // Writing into the file
            Files.writeString(fileName, resultBuilder.toString().toLowerCase());

            // Reading the content of the file
//            String fileContent = Files.readString(fileName);

            // Printing the content inside the file
//            System.out.println(fileContent);
        } catch (IOException e) {
            // Handling any I/O exceptions
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}