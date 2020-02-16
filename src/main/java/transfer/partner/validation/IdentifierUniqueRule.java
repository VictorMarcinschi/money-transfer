package transfer.partner.validation;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import transfer.partner.repository.ServicePartnerRepository;
import transfer.partner.rest.RegisterServicePartnerRequest;
import transfer.validation.SimpleValidationError;
import transfer.validation.ValidationError;
import transfer.validation.ValidationRule;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class IdentifierUniqueRule implements ValidationRule<RegisterServicePartnerRequest> {

    private final ServicePartnerRepository servicePartnerRepository;

    @Override
    public boolean passes(RegisterServicePartnerRequest request) {
        return servicePartnerRepository.findByIdentifier(request.getIdentifier()).isEmpty();
    }

    @Override
    public ValidationError getError() {
        return new SimpleValidationError(409,
                "Service partner with such identifier already registered");
    }
}
