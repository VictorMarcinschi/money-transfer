package transfer.rest;

import spark.Request;

public interface Handler {

    RestResponse handle(Request request);
}
