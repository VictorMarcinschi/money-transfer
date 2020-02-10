package transfer.moneytransfer.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import transfer.moneytransfer.model.RetrievalStatus;
import transfer.moneytransfer.model.UserAttribute;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Builder
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
class ConfirmRetrievalResponse {

    @JsonProperty
    private final UUID receiverIdentifier;

    @JsonProperty
    private final String transferIdentifier;

    @JsonProperty
    private final RetrievalStatus retrievalStatus;

    @JsonProperty
    private final RetrievalApproval approval;

    @RequiredArgsConstructor
    @Builder
    @Getter
    @ToString
    static class RetrievalApproval {

        @JsonProperty
        private final String transferIdentifier;

        @JsonProperty
        private final String partnerIdentifier;

        @JsonProperty
        private final String partnerApiBasePath;

        @JsonProperty
        private final UUID receiverIdentifier;

        @JsonProperty
        private final LocalDateTime confirmedAt;

        @JsonProperty
        private final UserAttribute confirmedVia;

        @JsonProperty
        private final String currencyCode;

        @JsonProperty
        private final BigDecimal amount;
    }
}
