package transfer.properties;

import dagger.Module;
import dagger.Provides;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.util.Properties;

import static transfer.properties.ApplicationProperties.DATASOURCE_PASSWORD;
import static transfer.properties.ApplicationProperties.DATASOURCE_URL;
import static transfer.properties.ApplicationProperties.DATASOURCE_USER;
import static transfer.properties.ApplicationProperties.DATE_PATTERN;
import static transfer.properties.ApplicationProperties.MIGRATIONS_CLEAN;
import static transfer.properties.ApplicationProperties.MIGRATIONS_ENABLED;
import static transfer.properties.ApplicationProperties.SERVER_PORT;
import static transfer.properties.ApplicationProperties.TIMESTAMP_PATTERN;

@Module
@RequiredArgsConstructor
public class ApplicationPropertiesModule {

    private static final String APPLICATION_PROPERTIES = "application.properties";
    private static final String STRING_FALSE = String.valueOf(false);

    private final String propertiesFileName;

    @Provides
    @Named(APPLICATION_PROPERTIES)
    Properties provideApplicationProperties() {
        return PropertiesUtil.readSystemOverridableProperties(getClass().getClassLoader(), propertiesFileName);
    }

    @Provides
    @Named(DATASOURCE_URL)
    String provideDatasourceUrl(@Named(APPLICATION_PROPERTIES) Properties properties) {
        return properties.getProperty(DATASOURCE_URL);
    }

    @Provides
    @Named(DATASOURCE_USER)
    String provideDatasourceUser(@Named(APPLICATION_PROPERTIES) Properties properties) {
        return properties.getProperty(DATASOURCE_USER);
    }

    @Provides
    @Named(DATASOURCE_PASSWORD)
    String provideDatasourcePassword(@Named(APPLICATION_PROPERTIES) Properties properties) {
        return properties.getProperty(DATASOURCE_PASSWORD);
    }

    @Provides
    @Named(SERVER_PORT)
    int provideServerPort(@Named(APPLICATION_PROPERTIES) Properties properties) {
        return Integer.valueOf(properties.getProperty(SERVER_PORT));
    }

    @Provides
    @Named(MIGRATIONS_ENABLED)
    boolean provideMigrationsEnabled(@Named(APPLICATION_PROPERTIES) Properties properties) {
        return Boolean.valueOf(properties.getProperty(MIGRATIONS_ENABLED, STRING_FALSE));
    }

    @Provides
    @Named(MIGRATIONS_CLEAN)
    boolean provideMigrationsClean(@Named(APPLICATION_PROPERTIES) Properties properties) {
        return Boolean.valueOf(properties.getProperty(MIGRATIONS_CLEAN, STRING_FALSE));
    }

    @Provides
    @Named(DATE_PATTERN)
    String provideDatePattern(@Named(APPLICATION_PROPERTIES) Properties properties) {
        return properties.getProperty(DATE_PATTERN);
    }

    @Provides
    @Named(TIMESTAMP_PATTERN)
    String provideTimestampPattern(@Named(APPLICATION_PROPERTIES) Properties properties) {
        return properties.getProperty(TIMESTAMP_PATTERN);
    }
}
