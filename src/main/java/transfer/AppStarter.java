package transfer;

import org.flywaydb.core.Flyway;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AppStarter {

    private final boolean enableMigrations;
    private final Flyway flyway;

    void start() {
        if (enableMigrations) {
            flyway.migrate();
        }
    }
}
