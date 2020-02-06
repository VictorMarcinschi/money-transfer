package transfer.partner.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import transfer.rest.RestModule;
import transfer.server.RouteDefinition;
import transfer.server.SparkRouteDefinition;

@Module(includes = RestModule.class)
public class ServicePartnerRestModule {

    @Provides
    static ServicePartnerController provideServicePartnerController() {
        return new ServicePartnerController();
    }

    @Provides
    @IntoSet
    @RouteDefinition
    static SparkRouteDefinition provideServicePartnerRouteDefinition(ServicePartnerController controller,
            ObjectMapper mapper) {

        return new ServicePartnerRouteDefinition(controller, mapper);
    }
}
