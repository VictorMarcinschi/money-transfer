package transfer.config.properties;

import dagger.Module;
import dagger.Provides;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.util.Properties;

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
    @Named(ApplicationProperties.DATASOURCE_URL)
    String provideDatasourceUrl(@Named(APPLICATION_PROPERTIES) Properties properties) {
        return properties.getProperty(ApplicationProperties.DATASOURCE_URL);
    }

    @Provides
    @Named(ApplicationProperties.DATASOURCE_USER)
    String provideDatasourceUser(@Named(APPLICATION_PROPERTIES) Properties properties) {
        return properties.getProperty(ApplicationProperties.DATASOURCE_USER);
    }

    @Provides
    @Named(ApplicationProperties.DATASOURCE_PASSWORD)
    String provideDatasourcePassword(@Named(APPLICATION_PROPERTIES) Properties properties) {
        return properties.getProperty(ApplicationProperties.DATASOURCE_PASSWORD);
    }

    @Provides
    @Named(ApplicationProperties.RELADOMO_CONFIG_LOCATION)
    String provideReladomoConfigLocation(@Named(APPLICATION_PROPERTIES) Properties properties) {
        return properties.getProperty(ApplicationProperties.RELADOMO_CONFIG_LOCATION);
    }

    @Provides
    @Named(ApplicationProperties.TX_TIMEOUT)
    int provideTxTimeout(@Named(APPLICATION_PROPERTIES) Properties properties) {
        return Integer.valueOf(properties.getProperty(ApplicationProperties.TX_TIMEOUT));
    }

    @Provides
    @Named(ApplicationProperties.SERVER_PORT)
    int provideServerPort(@Named(APPLICATION_PROPERTIES) Properties properties) {
        return Integer.valueOf(properties.getProperty(ApplicationProperties.SERVER_PORT));
    }

    @Provides
    @Named(ApplicationProperties.MIGRATIONS_ENABLED)
    boolean provideMigrationsEnabled(@Named(APPLICATION_PROPERTIES) Properties properties) {
        return Boolean.valueOf(properties.getProperty(ApplicationProperties.MIGRATIONS_ENABLED, STRING_FALSE));
    }

    @Provides
    @Named(ApplicationProperties.MIGRATIONS_CLEAN)
    boolean provideMigrationsClean(@Named(APPLICATION_PROPERTIES) Properties properties) {
        return Boolean.valueOf(properties.getProperty(ApplicationProperties.MIGRATIONS_CLEAN, STRING_FALSE));
    }

    @Provides
    @Named(ApplicationProperties.DATE_PATTERN)
    String provideDatePattern(@Named(APPLICATION_PROPERTIES) Properties properties) {
        return properties.getProperty(ApplicationProperties.DATE_PATTERN);
    }

    @Provides
    @Named(ApplicationProperties.TIMESTAMP_PATTERN)
    String provideTimestampPattern(@Named(APPLICATION_PROPERTIES) Properties properties) {
        return properties.getProperty(ApplicationProperties.TIMESTAMP_PATTERN);
    }
}
