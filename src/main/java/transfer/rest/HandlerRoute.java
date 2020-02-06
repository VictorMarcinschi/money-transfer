package transfer.rest;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
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

        var location = restResponse.getLocation();
        if (StringUtils.isNotBlank(location)) {
            response.header("Location", location);
        }

        return restResponse.getBody();
    }
}
