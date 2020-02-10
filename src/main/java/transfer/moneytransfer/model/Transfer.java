package transfer.moneytransfer.model;

import org.apache.commons.lang3.Validate;
import transfer.partner.model.ServicePartner;
import transfer.user.model.User;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

public class Transfer extends TransferAbstract {

    public Transfer() {
        super();
        // You must not modify this constructor. Mithra calls this internally.
        // You can call this constructor. You can also add new constructors.
    }

    public Transfer(String identifier, ServicePartner partner, User sender, User receiver,
            UserAttribute receiverAttribute, TransferFunds funds, LocalDate expiry, Clock clock) {

        this();

        Validate.isTrue(expiry.compareTo(LocalDate.now(clock)) >= 0);
        setIdentifier(identifier);
        setSendingServicePartner(partner);
        setSendingUser(sender);
        setReceivingUser(receiver);
        setReceiverAttribute(receiverAttribute.name());
        setCurrencyCode(funds.getCurrency());
        setAmount(funds.getAmount());
        setRetrievalExpiry(Date.from(expiry.atStartOfDay(clock.getZone()).toInstant()));
        setStatus(TransferStatus.SUBMITTED.name());

        var now = ZonedDateTime.now(clock).toInstant();
        setCreatedAt(Timestamp.from(now));
        setUpdatedAt(Timestamp.from(now));
    }

    public Retrieval attemptRetrieval(ServicePartner retrievingPartner, String confirmationCode,
            ZonedDateTime confirmationExpiry, ZonedDateTime now) {

        checkSubmitted();
        updateExpiredRetrievals(now);
        findPendingRetrieval().ifPresent(r -> {
            throw new IllegalStateException("Retrieval already pending");
        });

        var retrieval = new Retrieval(retrievingPartner, confirmationCode, confirmationExpiry, now);
        getRetrievals().add(retrieval);
        return retrieval;
    }

    public void updateExpiredRetrievals(ZonedDateTime now) {
        getRetrievals().stream()
                .filter(retrieval -> retrieval.shouldExpire(now))
                .forEach(retrieval -> retrieval.markExpired(now));
    }

    private void checkSubmitted() {
        if (status() != TransferStatus.SUBMITTED) {
            throw new IllegalStateException("Transfer can not be retrieved");
        }
    }

    public TransferStatus status() {
        return TransferStatus.valueOf(getStatus());
    }

    public boolean isRetrievable() {
        return status() == TransferStatus.SUBMITTED && findPendingRetrieval().isEmpty();
    }

    public void markExpiredIfNecessary(ZonedDateTime now) {
        if (status() == TransferStatus.SUBMITTED) {
            var expiry = LocalDate.ofInstant(Instant.ofEpochMilli(getRetrievalExpiry().getTime()), now.getZone());
            if (now.toLocalDate().compareTo(expiry) >= 0) {
                setStatus(TransferStatus.EXPIRED.name());
                setUpdatedAt(Timestamp.from(now.toInstant()));
            }
        }
    }

    private Optional<Retrieval> findPendingRetrieval() {
        return getRetrievals().stream()
                .filter(r -> r.status() == RetrievalStatus.PENDING_CONFIRM)
                .findFirst();
    }

    public Retrieval confirmRetrieval(ServicePartner retrievingPartner, String confirmationCode, ZonedDateTime now) {
        checkSubmitted();
        updateExpiredRetrievals(now);
        var retrieval = findPendingRetrieval()
                .orElseThrow(() -> new IllegalStateException("No pending retrieval"));

        retrieval.confirm(retrievingPartner, confirmationCode, now);
        setStatus(TransferStatus.RETRIEVED.name());
        setUpdatedAt(Timestamp.from(now.toInstant()));
        return retrieval;
    }

    public UserAttribute receiverAttribute() {
        return UserAttribute.valueOf(getReceiverAttribute());
    }

    public String receiver() {
        switch (receiverAttribute()) {
            case PHONE:
                return getReceivingUser().getPhone().get();
            case EMAIL:
                return getReceivingUser().getEmail().get();
        }
        throw new IllegalStateException("Invalid receiver attribute");
    }
}
