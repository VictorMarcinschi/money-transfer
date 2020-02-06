package transfer.rest;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Getter
public class RestResponse {

    private final int status;
    private final Object body;
    private final String location;
}
