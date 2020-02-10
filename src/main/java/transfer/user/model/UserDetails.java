package transfer.user.model;

import org.apache.commons.lang3.Validate;
import transfer.user.rest.EndUserDetails;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.Optional;

public class UserDetails extends UserDetailsAbstract {

    public UserDetails() {
        super();
        // You must not modify this constructor. Mithra calls this internally.
        // You can call this constructor. You can also add new constructors.
    }

    public UserDetails(Optional<String> email, Optional<String> phone, Clock clock) {
        Validate.isTrue(email.isPresent() || phone.isPresent(),
                "Either email or phone are required");

        email.ifPresent(e -> setEmail(e));
        phone.ifPresent(p -> setPhone(p));

        var now = ZonedDateTime.now(clock).toInstant();
        setCreatedAt(Timestamp.from(now));
        setUpdatedAt(Timestamp.from(now));
    }

    void updateFrom(EndUserDetails newDetails, ZonedDateTime now) {
        Validate.isTrue(newDetails.isFullDetails());
        setPhone(newDetails.getPhone());
        setEmail(newDetails.getEmail());
        setUpdatedAt(Timestamp.from(now.toInstant()));
    }
}
