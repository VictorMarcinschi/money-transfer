package transfer.rest;

import java.util.Collections;
import java.util.Map;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import transfer.domain.CommandResult;

@RequiredArgsConstructor
@Getter
public class RestResponse {

    private final int status;
    private final Object body;

    @Setter(AccessLevel.PRIVATE)
    private Map<String, String> headers = Collections.emptyMap();

    public static RestResponse from(CommandResult result, int successStatus, Map<String, String> successHeaders) {
        RestResponse response = createFrom(result, successStatus);
        if (result.isSuccessful()) {
            response.setHeaders(successHeaders);
            return response;
        }
        return response;
    }

    public static RestResponse from(CommandResult result, int successStatus) {
        return createFrom(result, successStatus);
    }

    private static RestResponse createFrom(CommandResult result, int successStatus) {
        RestResponse response;
        if (result.isSuccessful()) {
            response = new RestResponse(successStatus, result.getValue());
            return response;
        }
        response = new RestResponse(result.getError().httpStatus(), result.getError().message());
        return response;
    }
}
