package transfer.moneytransfer.rest;

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

    private final UUID senderIdentifier;
    private final String transferIdentifier;
}
