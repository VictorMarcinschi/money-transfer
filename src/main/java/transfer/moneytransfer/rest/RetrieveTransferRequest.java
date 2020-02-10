package transfer.moneytransfer.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
class RetrieveTransferRequest {

    @Setter(AccessLevel.PACKAGE)
    private String transferIdentifier;

    @JsonProperty
    private final String partnerIdentifier;
}
