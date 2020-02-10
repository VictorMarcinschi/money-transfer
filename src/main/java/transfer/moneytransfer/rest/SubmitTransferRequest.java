package transfer.moneytransfer.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import transfer.moneytransfer.model.TransferFunds;
import transfer.moneytransfer.model.UserAttribute;
import transfer.user.rest.EndUserDetails;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@RequiredArgsConstructor
@Builder
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
class SubmitTransferRequest {

    @JsonProperty
    private final String partnerIdentifier;

    @JsonProperty
    private final UUID senderIdentifier;

    @JsonProperty
    private final EndUserDetails senderDetails;

    @JsonProperty
    private final UserAttribute receiverAttribute;

    @JsonProperty
    private final String receiver;

    @JsonProperty
    private final String currencyCode;

    @JsonProperty
    private final BigDecimal amount;

    @JsonProperty
    private final LocalDate retrievalExpiry;

    public TransferFunds extractFunds() {
        return new TransferFunds(currencyCode, amount);
    }

}
