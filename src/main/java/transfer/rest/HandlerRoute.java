package transfer.rest;

import lombok.RequiredArgsConstructor;
import spark.Request;
import spark.Response;
import spark.Route;

@RequiredArgsConstructor
public class HandlerRoute implements Route {

    private final Handler handler;

    @Override
    public Object handle(Request request, Response response) {
        var restResponse = handler.handle(request);
        response.status(restResponse.getStatus());
        response.type("application/json");
        restResponse.getHeaders().entrySet().stream()
                .forEach(e -> response.header(e.getKey(), e.getValue()));

        return restResponse.getBody();
    }
}
