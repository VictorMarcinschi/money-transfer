package transfer.moneytransfer.model;

import org.apache.commons.lang3.Validate;
import transfer.partner.model.ServicePartner;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class Retrieval extends RetrievalAbstract {

    public Retrieval() {
        super();
        // You must not modify this constructor. Mithra calls this internally.
        // You can call this constructor. You can also add new constructors.
    }

    Retrieval(ServicePartner partner, String code, ZonedDateTime expiry, ZonedDateTime now) {
        this();

        Validate.isTrue(expiry.isAfter(now));
        setRetrievingServicePartner(partner);
        setStatus(RetrievalStatus.PENDING_CONFIRM.name());
        setConfirmationCode(code);
        setConfirmationExpiry(Timestamp.from(expiry.toInstant()));

        Instant instant = now.toInstant();
        setCreatedAt(Timestamp.from(instant));
        setUpdatedAt(Timestamp.from(instant));
    }

    public RetrievalStatus status() {
        return RetrievalStatus.valueOf(getStatus());
    }

    boolean shouldExpire(ZonedDateTime now) {
        var expiry = ZonedDateTime.ofInstant(Instant.ofEpochMilli(getConfirmationExpiry().getTime()), now.getZone());
        return expiry.compareTo(now) <= 0;
    }

    void markExpired(ZonedDateTime now) {
        if (status() == RetrievalStatus.CONFIRMED) {
            throw new IllegalStateException("Retrieval already confirmed");
        }
        if (status() == RetrievalStatus.PENDING_CONFIRM) {
            setStatus(RetrievalStatus.EXPIRED.name());
            setUpdatedAt(Timestamp.from(now.toInstant()));
        }
    }

    void confirm(ServicePartner partner, String code, ZonedDateTime now) {
        if (status() == RetrievalStatus.EXPIRED) {
            throw new IllegalStateException("Retrieval expired");
        }
        if (!getRetrievingServicePartner().isSame(partner)) {
            throw new IllegalArgumentException("Confirming partner and retrieving partner don't match");
        }
        if (!getConfirmationCode().equals(code)) {
            throw new IllegalArgumentException("Wrong confirmation code");
        }
        setStatus(RetrievalStatus.CONFIRMED.name());
        setConfirmedAt(Timestamp.from(now.toInstant()));
        setUpdatedAt(Timestamp.from(now.toInstant()));
    }

    public LocalDateTime confirmationExpiry() {
        return getConfirmationExpiry().toLocalDateTime();
    }

    public LocalDateTime confirmedAt() {
        return getConfirmedAt().toLocalDateTime();
    }

    public UserAttribute retrievalMethod() {
        return getTransfer().receiverAttribute();
    }

    public String receiver() {
        return getTransfer().receiver();
    }
}
