package transfer.moneytransfer.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import transfer.moneytransfer.model.Retrieval;
import transfer.moneytransfer.model.UserAttribute;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
@ToString
class RetrieveTransferResponse {

    @JsonProperty
    private final LocalDateTime confirmationDueBy;

    @JsonProperty
    private final String confirmationCode;

    @JsonProperty
    private final UserAttribute sendCodeVia;

    @JsonProperty
    private final String sendCodeTo;

    RetrieveTransferResponse(Retrieval retrieval) {
        this.confirmationDueBy = retrieval.confirmationExpiry();
        this.confirmationCode = retrieval.getConfirmationCode();
        this.sendCodeVia = retrieval.retrievalMethod();
        this.sendCodeTo = retrieval.receiver();
    }
}
