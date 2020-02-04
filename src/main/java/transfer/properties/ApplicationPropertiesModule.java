package transfer.properties;

import java.util.Properties;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import static transfer.properties.ApplicationProperties.DATASOURCE_PASSWORD;
import static transfer.properties.ApplicationProperties.DATASOURCE_URL;
import static transfer.properties.ApplicationProperties.DATASOURCE_USER;
import static transfer.properties.ApplicationProperties.SERVER_PORT;

@Module
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ApplicationPropertiesModule {

    private final Properties properties;

    @Provides
    @Named(DATASOURCE_URL)
    String provideDatasourceUrl() {
        return properties.getProperty(DATASOURCE_URL);
    }

    @Provides
    @Named(DATASOURCE_USER)
    String provideDatasourceUser() {
        return properties.getProperty(DATASOURCE_USER);
    }

    @Provides
    @Named(DATASOURCE_PASSWORD)
    String provideDatasourcePassword() {
        return properties.getProperty(DATASOURCE_PASSWORD);
    }

    @Provides
    @Named(SERVER_PORT)
    int provideServerPort() {
        return Integer.valueOf(properties.getProperty(DATASOURCE_PASSWORD));
    }
}
