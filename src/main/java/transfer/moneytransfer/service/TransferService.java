package transfer.moneytransfer.service;

import lombok.RequiredArgsConstructor;
import transfer.moneytransfer.model.Retrieval;
import transfer.moneytransfer.model.Transfer;
import transfer.moneytransfer.model.TransferFunds;
import transfer.moneytransfer.model.UserAttribute;
import transfer.moneytransfer.repository.TransferRepository;
import transfer.partner.model.ServicePartner;
import transfer.user.model.User;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Map;

@RequiredArgsConstructor
public class TransferService {

    private final TransferRepository transferRepository;
    private final TransferIdentifierGenerator identifierGenerator;
    private final RetrievalCodeGenerator retrievalCodeGenerator;
    private final Clock systemClock;
    private final Map<UserAttribute, Integer> retrievalValidityMinutes;

    public Transfer submitTransfer(ServicePartner partner, User sender, User receiver, UserAttribute receiverAttrbute,
            TransferFunds funds, LocalDate retrievalExpiry) {

        var transfer = new Transfer(identifierGenerator.generate(), partner, sender, receiver,
                receiverAttrbute, funds, retrievalExpiry, systemClock);

        transferRepository.create(transfer);
        return transfer;
    }

    public Retrieval retrieveTransfer(String transferIdentifier, ServicePartner retrievingPartner) {
        var now = ZonedDateTime.now(systemClock);
        var transfer = transferRepository.findByIdentifier(transferIdentifier)
                .orElseThrow(() -> new IllegalArgumentException("Wrong transfer identifier"));

        transfer.markExpiredIfNecessary(now);
        if (transfer.isRetrievable()) {
            var confirmationCode = retrievalCodeGenerator.generateFor(transfer);
            var retrievalExpiry = retrievalExpiryFor(transfer, now);
            return transfer.attemptRetrieval(retrievingPartner, confirmationCode, retrievalExpiry, now);
        }
        throw new IllegalStateException("Transfer not retrievable");
    }

    private ZonedDateTime retrievalExpiryFor(Transfer transfer, ZonedDateTime now) {
        return now.plusMinutes(retrievalValidityMinutes.get(transfer.receiverAttribute()));
    }

    public Retrieval confirmRetrieval(String transferIdentifier, String confirmationCode,
            ServicePartner retrievingPartner) {

        var now = ZonedDateTime.now(systemClock);
        var transfer = transferRepository.findByIdentifier(transferIdentifier)
                .orElseThrow(() -> new IllegalArgumentException("Wrong transfer identifier"));

        transfer.markExpiredIfNecessary(now);
        return transfer.confirmRetrieval(retrievingPartner, confirmationCode, now);
    }
}
