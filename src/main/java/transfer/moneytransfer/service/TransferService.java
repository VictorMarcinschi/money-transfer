package transfer.moneytransfer.service;

import lombok.RequiredArgsConstructor;
import transfer.moneytransfer.model.Transfer;
import transfer.moneytransfer.model.TransferFunds;
import transfer.moneytransfer.model.UserAttribute;
import transfer.moneytransfer.repository.TransferRepository;
import transfer.partner.model.ServicePartner;
import transfer.user.model.User;

import java.time.Clock;
import java.time.LocalDate;

@RequiredArgsConstructor
public class TransferService {

    private final TransferRepository transferRepository;
    private final TransferIdentifierGenerator identifierGenerator;
    private final Clock systemClock;

    public Transfer submitTransfer(ServicePartner partner, User sender, User receiver, UserAttribute receiverAttrbute,
            TransferFunds funds, LocalDate retrievalExpiry) {

        var transfer = new Transfer(identifierGenerator.generate(), partner, sender, receiver,
                receiverAttrbute, funds, retrievalExpiry, systemClock);

        transferRepository.create(transfer);

        return transfer;
    }
}
