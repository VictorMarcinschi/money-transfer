package transfer.user.model;

import transfer.user.rest.EndUserDetails;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

public class User extends UserAbstract {

    public User() {
        super();
        // You must not modify this constructor. Mithra calls this internally.
        // You can call this constructor. You can also add new constructors.
    }

    public User(UUID identifier, Optional<String> email, Optional<String> phone, Clock clock) {
        setIdentifier(identifier.toString());
        setUserDetails(new UserDetails(email, phone, clock));

        var now = ZonedDateTime.now(clock).toInstant();
        setCreatedAt(Timestamp.from(now));
        setUpdatedAt(Timestamp.from(now));
    }

    public Optional<String> getEmail() {
        return Optional.ofNullable(getUserDetails().getEmail());
    }

    public Optional<String> getPhone() {
        return Optional.ofNullable(getUserDetails().getPhone());
    }

    public UUID getUUIDIdentifier() {
        return UUID.fromString(getIdentifier());
    }

    public void updateDetails(EndUserDetails newDetails, ZonedDateTime now) {
        getUserDetails().updateFrom(newDetails, now);
    }
}
