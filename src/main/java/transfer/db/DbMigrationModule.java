package transfer.db;

import dagger.Module;
import dagger.Provides;
import org.flywaydb.core.Flyway;

import javax.inject.Named;
import javax.sql.DataSource;

import static transfer.properties.ApplicationProperties.MIGRATIONS_CLEAN;
import static transfer.properties.ApplicationProperties.MIGRATIONS_ENABLED;

@Module(includes = DatabaseModule.class)
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
}
