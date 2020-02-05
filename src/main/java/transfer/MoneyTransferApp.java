package transfer;

import transfer.db.DaggerDbMigrationStarterFactory;
import transfer.properties.ApplicationPropertiesModule;
import transfer.server.DaggerServerConfigurerFactory;

public class MoneyTransferApp {

    public static void main(String[] args) {
        var applicationProperties = new ApplicationPropertiesModule("application.properties");
        migrateDatabase(applicationProperties);
        configureServer(applicationProperties);
    }

    private static void migrateDatabase(ApplicationPropertiesModule applicationProperties) {
        var migration = DaggerDbMigrationStarterFactory.builder()
                .applicationPropertiesModule(applicationProperties)
                .build()
                .starter();

        migration.start();
    }

    private static void configureServer(ApplicationPropertiesModule applicationProperties) {
        var configurer = DaggerServerConfigurerFactory.builder()
                .applicationPropertiesModule(applicationProperties)
                .build()
                .configurer();

        configurer.configure();
    }
}
