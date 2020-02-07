package transfer.partner.model;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.LocalDate;

public class ServicePartner extends ServicePartnerAbstract {

    public ServicePartner() {
        super();
        // You must not modify this constructor. Mithra calls this internally.
        // You can call this constructor. You can also add new constructors.
    }

    public ServicePartner(String identifier, LocalDate kycExpiry, String apiBasePath, Clock clock) {
        setIdentifier(identifier);
        setKycExpiry(Timestamp.from(kycExpiry.atStartOfDay(clock.getZone()).toInstant()));
        setApiBasePath(apiBasePath);
    }

    public LocalDate kycExpiry() {
        return LocalDate.from(getKycExpiry().toInstant());
    }

    public boolean isKycValid(Clock clock) {
        return LocalDate.now(clock).isBefore(kycExpiry());
    }
}
