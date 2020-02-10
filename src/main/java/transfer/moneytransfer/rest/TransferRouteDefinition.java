package transfer.moneytransfer.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import transfer.rest.HandlerRoute;
import transfer.rest.JsonDeserializingHandler;
import transfer.server.SparkRouteDefinition;

import static spark.Spark.patch;
import static spark.Spark.post;

@RequiredArgsConstructor
class TransferRouteDefinition implements SparkRouteDefinition {

    private final TransferController controller;
    private final ObjectMapper mapper;

    @Override
    public void define() {
        post("/transfers",
                new HandlerRoute(new JsonDeserializingHandler<>(
                        (dr, p, r) -> controller.submitTransfer(dr, r),
                        mapper,
                        SubmitTransferRequest.class)),
                mapper::writeValueAsString);

        post("/transfers/:transferIdentifier/retrievals",
                new HandlerRoute(new JsonDeserializingHandler<>(
                        (dr, p, r) -> controller.retrieveTransfer(dr, p, r),
                        mapper,
                        RetrieveTransferRequest.class)),
                mapper::writeValueAsString);

        patch("/transfers/:transferIdentifier/retrievals/:partnerIdentifier",
                new HandlerRoute(new JsonDeserializingHandler<>(
                        (dr, p, r) -> controller.confirmRetrieval(dr, p),
                        mapper,
                        ConfirmRetrievalRequest.class)),
                mapper::writeValueAsString);
    }
}
