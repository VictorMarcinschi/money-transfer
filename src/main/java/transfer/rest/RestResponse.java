package transfer.rest;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import transfer.domain.CommandResult;

@RequiredArgsConstructor
@Builder
@Getter
public class RestResponse {

    private final int status;
    private final Object body;
    private final String location;

    public static RestResponse.RestResponseBuilder builderFrom(CommandResult result, int successStatus) {
        var builder = RestResponse.builder();
        if (result.isSuccessful()) {
            return builder
                    .status(successStatus)
                    .body(result.getValue());
        }

        var error = result.getError();
        return builder
                .status(error.httpStatus())
                .body(error.message());
    }
}
