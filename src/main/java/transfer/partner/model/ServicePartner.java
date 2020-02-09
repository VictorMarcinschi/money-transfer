package transfer.partner.model;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;

public class ServicePartner extends ServicePartnerAbstract {

    ServicePartner() {
        super();
        // You must not modify this constructor. Mithra calls this internally.
        // You can call this constructor. You can also add new constructors.
    }

    public ServicePartner(String identifier, LocalDate kycExpiry, String apiBasePath, Clock clock) {
        setIdentifier(identifier);
        setKycExpiry(Timestamp.from(kycExpiry.atStartOfDay(clock.getZone()).toInstant()));
        setApiBasePath(apiBasePath);

        var now = ZonedDateTime.now(clock).toInstant();
        setCreatedAt(Timestamp.from(now));
        setUpdatedAt(Timestamp.from(now));
    }

    public LocalDate kycExpiry() {
        return LocalDate.from(getKycExpiry().toInstant());
    }

    public boolean isKycValid(Clock clock) {
        return LocalDate.now(clock).isBefore(kycExpiry());
    }
}
