package transfer.config.properties;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApplicationProperties {

    public static final String DATASOURCE_URL = "datasource.url";
    public static final String DATASOURCE_USER = "datasource.user";
    public static final String DATASOURCE_PASSWORD = "datasource.password";

    public static final String RELADOMO_CONFIG_LOCATION = "reladomo.config.localtion";
    public static final String TX_TIMEOUT = "tx.timeout";

    public static final String MIGRATIONS_ENABLED = "migrations.enabled";
    public static final String MIGRATIONS_CLEAN = "migrations.clean";

    public static final String SERVER_PORT = "server.port";

    public static final String DATE_PATTERN = "defaults.pattern.date";
    public static final String TIMESTAMP_PATTERN = "defaults.pattern.timestamp";
}
