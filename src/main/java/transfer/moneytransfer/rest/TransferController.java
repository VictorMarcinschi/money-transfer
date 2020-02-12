package transfer.moneytransfer.rest;

import java.time.Clock;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import transfer.domain.Command;
import transfer.rest.RestResponse;

@RequiredArgsConstructor
class TransferController {

    private static final String PARAM_TRANSFER_IDENTIFIER = ":transferidentifier";
    private static final String PARAM_PARTNER_IDENTIFIER = ":partneridentifier";

    private final Command<SubmitTransferRequest, SubmitTransferResponse> submitTransferCommand;
    private final Command<RetrieveTransferRequest, RetrieveTransferResponse> retrieveTransferCommand;
    private final Command<ConfirmRetrievalRequest, ConfirmRetrievalResponse> confirmRetrievalCommand;
    private final Clock systemClock;

    RestResponse submitTransfer(SubmitTransferRequest transferRequest) {
        var result = submitTransferCommand.execute(transferRequest, systemClock);
        return RestResponse.from(result, 201);
    }

    RestResponse retrieveTransfer(RetrieveTransferRequest retrieveRequest, Map<String, String> params) {
        retrieveRequest.setTransferIdentifier(params.get(PARAM_TRANSFER_IDENTIFIER));
        var result = retrieveTransferCommand.execute(retrieveRequest, systemClock);
        return RestResponse.from(result, 201);
    }

    RestResponse confirmRetrieval(ConfirmRetrievalRequest confirmRequest, Map<String, String> params) {
        confirmRequest.setTransferIdentifier(params.get(PARAM_TRANSFER_IDENTIFIER));
        confirmRequest.setPartnerIdentifier(params.get(PARAM_PARTNER_IDENTIFIER));
        var result = confirmRetrievalCommand.execute(confirmRequest, systemClock);
        return RestResponse.from(result, 202);
    }
}
