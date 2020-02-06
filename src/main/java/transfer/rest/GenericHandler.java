package transfer.rest;

import java.util.Map;

import spark.Request;

public interface GenericHandler<R> {

    RestResponse handle(R deserializedRequest, Map<String, String> params, Request request);
}
