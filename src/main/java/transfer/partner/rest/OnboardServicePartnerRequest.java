package transfer.partner.rest;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import transfer.partner.model.ServicePartner;

import java.time.Clock;
import java.time.LocalDate;

@RequiredArgsConstructor
@Builder(access = AccessLevel.PACKAGE)
@ToString
@Getter
class OnboardServicePartnerRequest {

    private final String identifier;
    private final LocalDate kycExpiry;
    private final String apiBasePath;

    ServicePartner toServicePartner(Clock clock) {
        return new ServicePartner(identifier, kycExpiry, apiBasePath, clock);
    }
}
