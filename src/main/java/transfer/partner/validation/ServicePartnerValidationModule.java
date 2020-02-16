package transfer.partner.validation;

import dagger.Module;
import dagger.Provides;
import transfer.partner.repository.ServicePartnerRepository;
import transfer.partner.repository.ServicePartnerRepositoryModule;
import transfer.partner.rest.RegisterServicePartnerRequest;
import transfer.validation.ValidationRule;
import transfer.validation.Validator;

import javax.inject.Named;
import java.time.Clock;
import java.util.List;

@Module(includes = ServicePartnerRepositoryModule.class)
public class ServicePartnerValidationModule {

    public static final String REGISTER_PARTNER_VALIDATOR = "validation.partner.register";

    @Provides
    @Named(REGISTER_PARTNER_VALIDATOR)
    static Validator<RegisterServicePartnerRequest> provideRegisterServicePartnerValidator(Clock systemClock,
            ServicePartnerRepository servicePartnerRepository) {

        List<ValidationRule<RegisterServicePartnerRequest>> rules = List.of(
                new IdentifierRequiredRule(),
                new KycInFutureRule(systemClock),
                new ApiBasePathRequiredRule(),
                new IdentifierUniqueRule(servicePartnerRepository)
        );

        return new Validator<>(rules);
    }
}
