package transfer.partner.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public class ServicePartner {

    @Getter(AccessLevel.NONE)
    private Long id;

    private String identifier;

    @Getter(AccessLevel.NONE)
    private Date kycExpiry;

    private String apiBasePath;

    public ServicePartner(String identifier, LocalDate kycExpiry, String apiBasePath) {
        this.identifier = identifier;
        this.kycExpiry = Date.from(Instant.from(kycExpiry.atStartOfDay()));
        this.apiBasePath = apiBasePath;
    }

    public LocalDate getKycExpiry() {
        return LocalDate.from(kycExpiry.toInstant());
    }

    public boolean isKycValid(Clock clock) {
        return LocalDate.now(clock).isBefore(getKycExpiry());
    }
}
