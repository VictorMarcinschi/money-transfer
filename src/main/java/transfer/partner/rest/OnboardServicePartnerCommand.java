package transfer.partner.rest;

import lombok.RequiredArgsConstructor;
import transfer.domain.Command;
import transfer.domain.CommandResult;
import transfer.partner.repository.ServicePartnerRepository;
import transfer.validation.ValidationResult;

import java.time.Clock;

@RequiredArgsConstructor
class OnboardServicePartnerCommand implements Command<OnboardServicePartnerRequest, String> {

    private final ServicePartnerRepository servicePartnerRepository;
    private final Clock systemClock;

    @Override
    public CommandResult<String> execute(OnboardServicePartnerRequest request) {
        var partner = request.toServicePartner(systemClock);
        servicePartnerRepository.create(partner);

        return CommandResult.<String>builder()
                .validationResult(new ValidationResult())
                .value(partner.getIdentifier())
                .build();
    }
}
