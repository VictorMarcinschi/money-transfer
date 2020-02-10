package transfer.moneytransfer.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@RequiredArgsConstructor
@Builder
@Getter
@ToString
class SubmitTransferResponse {

    @JsonProperty
    private final UUID senderIdentifier;

    @JsonProperty
    private final String transferIdentifier;
}
