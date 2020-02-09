package transfer.moneytransfer.model;

import transfer.partner.model.ServicePartner;
import transfer.user.model.User;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;

public class Transfer extends TransferAbstract {

    Transfer() {
        super();
        // You must not modify this constructor. Mithra calls this internally.
        // You can call this constructor. You can also add new constructors.
    }

    public Transfer(String identifier, ServicePartner partner, User sender, User receiver,
            UserAttribute receiverAttribute, TransferFunds funds, LocalDate expiry, Clock clock) {

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
}
