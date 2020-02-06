package transfer.partner.rest;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import transfer.partner.model.ServicePartner;

import java.time.LocalDate;

@RequiredArgsConstructor
@Builder
@ToString
@Getter
class NewServicePartnerRequest {

    private final String identifier;
    private final LocalDate kycExpiry;
    private final String apiBasePath;

    ServicePartner toServicePartner() {
        return new ServicePartner(identifier, kycExpiry, apiBasePath);
    }
}
