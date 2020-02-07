package transfer.server;

import java.util.Set;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import transfer.config.properties.ApplicationPropertiesModule;
import transfer.partner.rest.ServicePartnerRestModule;
import transfer.rest.RestModule;

import static transfer.config.properties.ApplicationProperties.SERVER_PORT;

@Module(includes = {ApplicationPropertiesModule.class, RestModule.class, ServicePartnerRestModule.class})
class ServerConfigurerModule {

    @Provides
    static ServerConfigurer provideServerConfigurer(@Named(SERVER_PORT) int port,
            @RouteDefinition Set<SparkRouteDefinition> routeDefinitions) {

        return new SparkServerConfigurer(port, routeDefinitions);
    }
}
