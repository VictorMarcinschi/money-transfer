package transfer.moneytransfer.rest;

import lombok.RequiredArgsConstructor;
import spark.Request;
import transfer.domain.Command;
import transfer.rest.RestResponse;

import java.util.Map;

@RequiredArgsConstructor
class TransferController {

    private static final String PARAM_TRANSFER_IDENTIFIER = ":transferidentifier";
    private static final String PARAM_PARTNER_IDENTIFIER = ":partneridentifier";

    private final Command<SubmitTransferRequest, SubmitTransferResponse> submitTransferCommand;
    private final Command<RetrieveTransferRequest, RetrieveTransferResponse> retrieveTransferCommand;
    private final Command<ConfirmRetrievalRequest, ConfirmRetrievalResponse> confirmRetrievalCommand;

    RestResponse submitTransfer(SubmitTransferRequest transferRequest, Request request) {
        var result = submitTransferCommand.execute(transferRequest);
        return RestResponse.builderFrom(result, 201)
                .location(request.matchedPath() + "/" + result.getValue().getTransferIdentifier())
                .build();
    }

    RestResponse retrieveTransfer(RetrieveTransferRequest retrieveRequest, Map<String, String> params,
            Request request) {

        retrieveRequest.setTransferIdentifier(params.get(PARAM_TRANSFER_IDENTIFIER));
        var result = retrieveTransferCommand.execute(retrieveRequest);
        return RestResponse.builderFrom(result, 201)
                .location(request.matchedPath() + "/" + retrieveRequest.getPartnerIdentifier())
                .build();
    }

    RestResponse confirmRetrieval(ConfirmRetrievalRequest confirmRequest, Map<String, String> params) {
        confirmRequest.setTransferIdentifier(params.get(PARAM_TRANSFER_IDENTIFIER));
        confirmRequest.setPartnerIdentifier(params.get(PARAM_PARTNER_IDENTIFIER));
        var result = confirmRetrievalCommand.execute(confirmRequest);
        return RestResponse.builderFrom(result, 202).build();
    }
}
