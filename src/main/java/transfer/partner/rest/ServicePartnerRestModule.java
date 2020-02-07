package transfer.partner.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import transfer.domain.Command;
import transfer.rest.RestModule;
import transfer.server.RouteDefinition;
import transfer.server.SparkRouteDefinition;

import javax.inject.Named;

import static transfer.partner.rest.ServicePartnerCommandsModule.COMMAND_ONBOARD;

@Module(includes = {RestModule.class, ServicePartnerCommandsModule.class})
public class ServicePartnerRestModule {

    @Provides
    static ServicePartnerController provideServicePartnerController(
            @Named(COMMAND_ONBOARD) Command<OnboardServicePartnerRequest, String> onboardCommand) {

        return new ServicePartnerController(onboardCommand);
    }

    @Provides
    @IntoSet
    @RouteDefinition
    static SparkRouteDefinition provideServicePartnerRouteDefinition(ServicePartnerController controller,
            ObjectMapper mapper) {

        return new ServicePartnerRouteDefinition(controller, mapper);
    }
}
