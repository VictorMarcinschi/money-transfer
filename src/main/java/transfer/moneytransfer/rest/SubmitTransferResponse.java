package transfer.moneytransfer.rest;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import transfer.moneytransfer.model.UserAttribute;

@RequiredArgsConstructor
@Builder
@Getter
@ToString
class SubmitTransferResponse {

    @JsonProperty
    private final UUID senderIdentifier;

    @JsonProperty
    private final String transferIdentifier;

    @JsonProperty
    private final LocalDate retrievalDueBy;

    @JsonProperty
    private final UserAttribute sendNotificationVia;

    @JsonProperty
    private final String sendNotificationTo;
}
