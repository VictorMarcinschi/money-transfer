package transfer.moneytransfer.rest;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import transfer.moneytransfer.model.TransferFunds;
import transfer.moneytransfer.model.UserAttribute;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@RequiredArgsConstructor
@Builder
@Getter
@ToString
class SubmitTransferRequest {

    private final String partnerIdentifier;
    private final UUID senderIdentifier;
    private final Sender senderDetails;
    private final UserAttribute receiverAttribute;
    private final String receiver;
    private final String currencyCode;
    private final BigDecimal amount;
    private final LocalDate retrievalExpiry;

    public TransferFunds extractFunds() {
        return new TransferFunds(currencyCode, amount);
    }

    @RequiredArgsConstructor
    @Getter
    static class Sender {

        private final String email;
        private final String phone;
    }
}
