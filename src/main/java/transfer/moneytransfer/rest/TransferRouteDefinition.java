package transfer.moneytransfer.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import transfer.rest.HandlerRoute;
import transfer.rest.JsonDeserializingHandler;
import transfer.server.SparkRouteDefinition;

import static spark.Spark.post;

@RequiredArgsConstructor
class TransferRouteDefinition implements SparkRouteDefinition {

    private final TransferController controller;
    private final ObjectMapper mapper;

    @Override
    public void define() {
        post("/transfers", new HandlerRoute(new JsonDeserializingHandler<>(
                        (dr, p, r) -> controller.submitTransfer(dr, r),
                        mapper,
                        SubmitTransferRequest.class)),
                mapper::writeValueAsString);
    }
}
