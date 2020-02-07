package transfer.server;

import dagger.Module;
import dagger.Provides;
import transfer.partner.rest.ServicePartnerRestModule;
import transfer.config.properties.ApplicationPropertiesModule;

import javax.inject.Named;
import java.util.Set;

import static transfer.config.properties.ApplicationProperties.SERVER_PORT;

@Module(includes = {ApplicationPropertiesModule.class, ServicePartnerRestModule.class})
class ServerConfigurerModule {

    @Provides
    static ServerConfigurer provideServerConfigurer(@Named(SERVER_PORT) int port,
            @RouteDefinition Set<SparkRouteDefinition> routeDefinitions) {

        return new SparkServerConfigurer(port, routeDefinitions);
    }
}
