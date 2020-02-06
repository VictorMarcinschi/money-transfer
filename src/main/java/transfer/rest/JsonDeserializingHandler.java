package transfer.rest;

import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import spark.Request;

@Slf4j
@RequiredArgsConstructor
public class JsonDeserializingHandler<R> implements Handler {

    private final GenericHandler<R> handler;
    private final ObjectMapper mapper;
    private final Class<R> requestType;

    @Override
    public RestResponse handle(Request request) {
        log.info("Received request with body\n{}", request.body());
        var deserializedRequest = deserializeBody(request.body());
        return deserializedRequest.map(r -> handler.handle(r, request.params(), request))
                .orElse(RestResponse.builder()
                        .status(400)
                        .body("Could not understand request body")
                        .build());
    }

    private Optional<R> deserializeBody(String body) {
        try {
            return Optional.of(mapper.readValue(body, requestType));
        } catch (JsonProcessingException e) {
            return Optional.empty();
        }
    }
}
