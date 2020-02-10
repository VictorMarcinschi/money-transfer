package transfer.moneytransfer.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import transfer.user.rest.EndUserDetails;

import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
@ToString
class ConfirmRetrievalRequest {

    @Setter(AccessLevel.PACKAGE)
    private String partnerIdentifier;

    @Setter(AccessLevel.PACKAGE)
    private String transferIdentifier;

    @JsonProperty
    private final UUID receiverIdentifier;

    @JsonProperty
    private final EndUserDetails receiverDetails;

    @JsonProperty
    private final String confirmationCode;
}
