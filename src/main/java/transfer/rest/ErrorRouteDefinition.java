package transfer.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import spark.Spark;
import transfer.rest.ErrorResponse;
import transfer.server.SparkRouteDefinition;

import static spark.Spark.exception;
import static spark.Spark.internalServerError;
import static spark.Spark.notFound;

@RequiredArgsConstructor
class ErrorRouteDefinition implements SparkRouteDefinition {

    private static final String ERROR_RESPONSE_TYPE = "application/json";

    private final ObjectMapper mapper;

    @Override
    public void define() {
        notFound((req, res) -> {
            res.type(ERROR_RESPONSE_TYPE);
            return mapper.writeValueAsString(new ErrorResponse("Not Found"));
        });

        internalServerError((req, res) -> {
            res.type(ERROR_RESPONSE_TYPE);
            return mapper.writeValueAsString(new ErrorResponse("Internal Server Error"));
        });

        mapException(IllegalArgumentException.class, 404);
        mapException(IllegalStateException.class, 403);
    }

    private void mapException(Class<? extends Throwable> exceptionType, int status) {
        exception(IllegalArgumentException.class, (exception, request, response) -> {
            response.type(ERROR_RESPONSE_TYPE);
            response.status(status);
            try {
                response.body(mapper.writeValueAsString(new ErrorResponse(exception.getMessage())));
            } catch (JsonProcessingException e) {
                response.body(exception.getMessage());
            }
        });
    }
}
