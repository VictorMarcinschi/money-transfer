package transfer.partner.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import transfer.partner.model.ServicePartner;

import java.time.Clock;
import java.time.LocalDate;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Builder(access = AccessLevel.PACKAGE)
@ToString
@Getter
public class RegisterServicePartnerRequest {

    @JsonProperty
    private final String identifier;

    @JsonProperty
    private final LocalDate kycExpiry;

    @JsonProperty
    private final String apiBasePath;

    ServicePartner toServicePartner(Clock clock) {
        return new ServicePartner(identifier, kycExpiry, apiBasePath, clock);
    }
}
