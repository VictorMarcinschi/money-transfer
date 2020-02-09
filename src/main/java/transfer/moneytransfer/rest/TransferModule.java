package transfer.moneytransfer.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import transfer.domain.Command;
import transfer.domain.TransactionalCommand;
import transfer.moneytransfer.repository.TransferRepositoryModule;
import transfer.moneytransfer.service.TransferService;
import transfer.moneytransfer.service.TransferServiceModule;
import transfer.partner.repository.ServicePartnerRepository;
import transfer.partner.repository.ServicePartnerRepositoryModule;
import transfer.rest.RestModule;
import transfer.server.RouteDefinition;
import transfer.server.SparkRouteDefinition;
import transfer.user.repository.UserRepository;
import transfer.user.repository.UserRepositoryModule;
import transfer.user.service.UserService;
import transfer.user.service.UserServiceModule;

import javax.inject.Named;

@Module(includes = {RestModule.class, TransferRepositoryModule.class, ServicePartnerRepositoryModule.class,
        UserRepositoryModule.class, UserServiceModule.class, TransferServiceModule.class})
public class TransferModule {

    private static final String COMMAND_SUBMIT = "submitTransferCommand";

    @Provides
    @IntoSet
    @RouteDefinition
    static SparkRouteDefinition provideTransferRouteDefinition(TransferController controller, ObjectMapper mapper) {
        return new TransferRouteDefinition(controller, mapper);
    }

    @Provides
    static TransferController provideTransferController(
            @Named(COMMAND_SUBMIT)Command<SubmitTransferRequest, SubmitTransferResponse> submitCommand) {

        return new TransferController(submitCommand);
    }

    @Provides
    @Named(COMMAND_SUBMIT)
    static Command<SubmitTransferRequest, SubmitTransferResponse> provideSubmitCommand(
            ServicePartnerRepository servicePartnerRepository,
            UserRepository userRepository,
            UserService userService,
            TransferService transferService) {

        var command = new SubmitTransferCommand(servicePartnerRepository, userRepository, userService, transferService);
        return new TransactionalCommand<>(command);
    }
}
