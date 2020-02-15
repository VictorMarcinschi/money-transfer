package transfer.db;

import dagger.Module;
import dagger.Provides;
import transfer.config.properties.ApplicationPropertiesModule;

import javax.inject.Named;

import java.time.Clock;

import static transfer.config.properties.ApplicationProperties.DATASOURCE_PASSWORD;
import static transfer.config.properties.ApplicationProperties.DATASOURCE_URL;
import static transfer.config.properties.ApplicationProperties.DATASOURCE_USER;

@Module(includes = {ApplicationPropertiesModule.class, DbMigrationModule.class})
class DatabaseModule {

    @Provides
    static ReladomoStarter provideReladomoStarter(@Named(DATASOURCE_URL) String connectionString,
            @Named(DATASOURCE_USER) String user,
            @Named(DATASOURCE_PASSWORD) String password,
            Clock systemClock) {

        return new ReladomoStarter(connectionString, user, password, systemClock);
    }
}
