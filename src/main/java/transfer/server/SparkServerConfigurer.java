package transfer.server;

import java.util.Collection;
import java.util.List;

import lombok.RequiredArgsConstructor;

import static spark.Spark.port;

@RequiredArgsConstructor
class SparkServerConfigurer implements ServerConfigurer {

    private final int port;
    private final Collection<SparkRouteDefinition> sparkRouteDefinitions;

    @Override
    public void configure() {
        port(port);
        sparkRouteDefinitions.forEach(SparkRouteDefinition::define);
    }
}
