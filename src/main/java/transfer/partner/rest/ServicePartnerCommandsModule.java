package transfer.partner.rest;

import dagger.Module;
import dagger.Provides;
import transfer.config.ClockModule;
import transfer.domain.Command;
import transfer.domain.TransactionalCommand;
import transfer.partner.repository.ServicePartnerRepository;
import transfer.partner.repository.ServicePartnerRepositoryModule;

import javax.inject.Named;
import java.time.Clock;

@Module(includes = {ServicePartnerRepositoryModule.class, ClockModule.class})
public class ServicePartnerCommandsModule {

    static final String COMMAND_ONBOARD = "onboardPartnerCommand";

    @Provides
    @Named(COMMAND_ONBOARD)
    static Command<OnboardServicePartnerRequest, String> onboardCommand(ServicePartnerRepository repository,
            Clock systemClock) {

        var command = new OnboardServicePartnerCommand(repository, systemClock);
        return new TransactionalCommand<>(command);
    }
}
