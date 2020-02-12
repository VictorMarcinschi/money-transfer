package transfer.server;

import java.util.Collection;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static spark.Spark.port;

@Slf4j
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
