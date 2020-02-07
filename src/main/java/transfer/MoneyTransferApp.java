package transfer;

import transfer.config.properties.ApplicationPropertiesModule;
import transfer.db.DaggerDatabaseTools;
import transfer.db.DatabaseTools;
import transfer.server.DaggerServerConfigurerFactory;

public class MoneyTransferApp {

    public static void main(String[] args) {
        var applicationProperties = new ApplicationPropertiesModule("application.properties");
        setupDatabase(applicationProperties);
        configureServer(applicationProperties);
    }

    private static void setupDatabase(ApplicationPropertiesModule applicationProperties) {
        DatabaseTools dbTools = DaggerDatabaseTools.builder()
                .applicationPropertiesModule(applicationProperties)
                .build();

        dbTools.migration().start();
        dbTools.reladomo().start();
    }

    private static void configureServer(ApplicationPropertiesModule applicationProperties) {
        var configurer = DaggerServerConfigurerFactory.builder()
                .applicationPropertiesModule(applicationProperties)
                .build()
                .configurer();

        configurer.configure();
    }
}
