package transfer.partner.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import transfer.domain.Command;
import transfer.domain.TransactionalCommand;
import transfer.domain.ValidatedCommand;
import transfer.partner.repository.ServicePartnerRepository;
import transfer.partner.repository.ServicePartnerRepositoryModule;
import transfer.partner.validation.ServicePartnerValidationModule;
import transfer.rest.RestModule;
import transfer.server.RouteDefinition;
import transfer.server.SparkRouteDefinition;
import transfer.validation.Validator;

import javax.inject.Named;

import java.time.Clock;

import static transfer.partner.validation.ServicePartnerValidationModule.REGISTER_PARTNER_VALIDATOR;

@Module(includes = {RestModule.class, ServicePartnerRepositoryModule.class, ServicePartnerValidationModule.class})
public class ServicePartnerModule {

    private static final String COMMAND_ONBOARD = "onboardPartnerCommand";

    @Provides
    @IntoSet
    @RouteDefinition
    static SparkRouteDefinition provideServicePartnerRouteDefinition(ServicePartnerController controller,
            ObjectMapper mapper) {

        return new ServicePartnerRouteDefinition(controller, mapper);
    }

    @Provides
    static ServicePartnerController provideServicePartnerController(
            @Named(COMMAND_ONBOARD) Command<RegisterServicePartnerRequest, String> onboardCommand, Clock systemClock) {

        return new ServicePartnerController(onboardCommand, systemClock);
    }

    @Provides
    @Named(COMMAND_ONBOARD)
    static Command<RegisterServicePartnerRequest, String> onboardCommand(ServicePartnerRepository repository,
            @Named(REGISTER_PARTNER_VALIDATOR) Validator<RegisterServicePartnerRequest> registerPartnerValidator) {

        var command = new RegisterServicePartnerCommand(repository);
        return new TransactionalCommand<>(new ValidatedCommand<>(command, registerPartnerValidator));
    }
}
