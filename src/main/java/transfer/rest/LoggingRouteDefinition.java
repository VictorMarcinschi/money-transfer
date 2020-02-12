package transfer.rest;

import lombok.extern.slf4j.Slf4j;
import transfer.server.SparkRouteDefinition;

import static spark.Spark.after;
import static spark.Spark.before;

@Slf4j
class LoggingRouteDefinition implements SparkRouteDefinition {

    @Override
    public void define() {
        before((req, res) -> {
            log.info("Received request {} {} with params {}\n{}", req.requestMethod(), req.pathInfo(),
                    req.params(), req.body());
        });

        after((req, res) -> {
            log.info("Sending response for {} {}\n{}", req.requestMethod(), req.pathInfo(), res.body());
        });
    }
}
