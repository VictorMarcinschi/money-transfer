package transfer.db;

import org.flywaydb.core.Flyway;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class DbMigrationStarter {

    private final boolean enableMigrations;
    private final boolean cleanMigrations;
    private final Flyway flyway;

    public void start() {
        if (enableMigrations) {
            if (cleanMigrations) {
                flyway.clean();
            }
            flyway.repair();
            flyway.migrate();
        }
    }
}
