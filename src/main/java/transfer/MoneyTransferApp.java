package transfer;

import transfer.db.DaggerDbMigrationStarterFactory;
import transfer.properties.ApplicationPropertiesModule;

public class MoneyTransferApp {

    public static void main(String[] args) {
        migrateDatabase();
    }

    private static void migrateDatabase() {
        var migration = DaggerDbMigrationStarterFactory.builder()
                .applicationPropertiesModule(new ApplicationPropertiesModule("application.properties"))
                .build()
                .starter();

        migration.start();
    }
}
