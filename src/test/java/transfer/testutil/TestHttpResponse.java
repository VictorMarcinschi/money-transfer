package transfer.testutil;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import okhttp3.Response;

@RequiredArgsConstructor
@Getter
public class TestHttpResponse<RES> {

    private final Response rawResponse;
    private final RES response;
}
