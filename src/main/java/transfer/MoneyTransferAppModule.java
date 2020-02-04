package transfer;

import dagger.Module;
import dagger.Provides;
import org.flywaydb.core.Flyway;
import transfer.db.DatabaseModule;

import javax.inject.Named;

import static transfer.properties.ApplicationProperties.MIGRATIONS_CLEAN;
import static transfer.properties.ApplicationProperties.MIGRATIONS_ENABLED;

@Module(includes = DatabaseModule.class)
class MoneyTransferAppModule {

    @Provides
    static AppStarter provideAppStarter(
            @Named(MIGRATIONS_ENABLED) boolean migrationsEnabled,
            @Named(MIGRATIONS_CLEAN) boolean migrationsClean,
            Flyway flyway) {

        return new AppStarter(migrationsEnabled, migrationsClean, flyway);
    }
}
