package transfer.db;

import dagger.Module;
import dagger.Provides;
import transfer.config.properties.ApplicationPropertiesModule;

import javax.inject.Named;

import java.time.Clock;

import static transfer.config.properties.ApplicationProperties.DATASOURCE_PASSWORD;
import static transfer.config.properties.ApplicationProperties.DATASOURCE_URL;
import static transfer.config.properties.ApplicationProperties.DATASOURCE_USER;
import static transfer.config.properties.ApplicationProperties.RELADOMO_CONFIG_LOCATION;
import static transfer.config.properties.ApplicationProperties.TX_TIMEOUT;

@Module(includes = {ApplicationPropertiesModule.class, DbMigrationModule.class})
class DatabaseModule {

    @Provides
    static ReladomoStarter provideReladomoStarter(@Named(DATASOURCE_URL) String connectionString,
            @Named(DATASOURCE_USER) String user,
            @Named(DATASOURCE_PASSWORD) String password,
            @Named(RELADOMO_CONFIG_LOCATION) String configLocation,
            @Named(TX_TIMEOUT) int txTimeout,
            Clock systemClock) {

        return new ReladomoStarter(connectionString, user, password, configLocation, txTimeout, systemClock);
    }
}
