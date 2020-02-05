package transfer.server;

import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import transfer.properties.ApplicationPropertiesModule;

import static transfer.properties.ApplicationProperties.SERVER_PORT;

@Module(includes = ApplicationPropertiesModule.class)
class ServerConfigurerModule {

    @Provides
    static ServerConfigurer provideServerConfigurer(@Named(SERVER_PORT) int port) {
        return new SparkServerConfigurer(port, List.of());
    }
}
