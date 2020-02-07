package transfer.db;

import dagger.Module;
import dagger.Provides;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang3.StringUtils;
import org.flywaydb.core.Flyway;
import transfer.config.properties.ApplicationProperties;
import transfer.config.properties.ApplicationPropertiesModule;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.sql.DataSource;

import static transfer.config.properties.ApplicationProperties.DATASOURCE_PASSWORD;
import static transfer.config.properties.ApplicationProperties.DATASOURCE_URL;
import static transfer.config.properties.ApplicationProperties.DATASOURCE_USER;
import static transfer.config.properties.ApplicationProperties.MIGRATIONS_CLEAN;
import static transfer.config.properties.ApplicationProperties.MIGRATIONS_ENABLED;

@Module(includes = ApplicationPropertiesModule.class)
class DbMigrationModule {

    @Provides
    static DbMigrationStarter provideDbMigrationStarter(
            @Named(MIGRATIONS_ENABLED) boolean migrationsEnabled,
            @Named(MIGRATIONS_CLEAN) boolean migrationsClean,
            Flyway flyway) {

        return new DbMigrationStarter(migrationsEnabled, migrationsClean, flyway);
    }

    @Provides
    static Flyway provideFlyway(DataSource dataSource) {
        return Flyway.configure()
                .dataSource(dataSource)
                .load();
    }

    @Provides
    static DataSource provideDataSource(
            @Named(DATASOURCE_URL) String url,
            @Named(DATASOURCE_USER) String user,
            @Named(DATASOURCE_PASSWORD) String password) {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(user);

        if (StringUtils.isNotBlank(password)) {
            dataSource.setPassword(password);
        }

        return dataSource;
    }
}
