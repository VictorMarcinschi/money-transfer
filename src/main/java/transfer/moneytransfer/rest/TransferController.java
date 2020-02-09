package transfer.moneytransfer.rest;

import lombok.RequiredArgsConstructor;
import spark.Request;
import transfer.domain.Command;
import transfer.rest.RestResponse;

@RequiredArgsConstructor
class TransferController {

    private final Command<SubmitTransferRequest, SubmitTransferResponse> submitTransferCommand;

    RestResponse submitTransfer(SubmitTransferRequest transferRequest, Request request) {
        var result = submitTransferCommand.execute(transferRequest);
        return RestResponse.builderFrom(result, 201)
                .location(request.matchedPath() + "/" + result.getValue().getTransferIdentifier())
                .build();
    }
}
