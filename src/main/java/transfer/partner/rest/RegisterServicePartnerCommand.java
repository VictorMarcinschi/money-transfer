package transfer.partner.rest;

import lombok.RequiredArgsConstructor;
import transfer.domain.Command;
import transfer.domain.CommandResult;
import transfer.partner.repository.ServicePartnerRepository;
import transfer.validation.ValidationResult;

import java.time.Clock;

@RequiredArgsConstructor
class RegisterServicePartnerCommand implements Command<RegisterServicePartnerRequest, String> {

    private final ServicePartnerRepository servicePartnerRepository;

    @Override
    public CommandResult<String> execute(RegisterServicePartnerRequest request, Clock systemClock) {
        var partner = request.toServicePartner(systemClock);
        servicePartnerRepository.create(partner);

        return CommandResult.<String>builder()
                .value(partner.getIdentifier())
                .build();
    }
}
