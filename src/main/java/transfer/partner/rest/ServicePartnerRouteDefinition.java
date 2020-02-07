package transfer.partner.rest;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import transfer.rest.HandlerRoute;
import transfer.rest.JsonDeserializingHandler;
import transfer.server.SparkRouteDefinition;

import static spark.Spark.post;

@RequiredArgsConstructor
class ServicePartnerRouteDefinition implements SparkRouteDefinition {

    private final ServicePartnerController controller;
    private final ObjectMapper mapper;

    @Override
    public void define() {
        post("/partners", new HandlerRoute(new JsonDeserializingHandler<>(
                        (dr, p, r) -> controller.onboardServicePartner(dr, r),
                        mapper,
                        OnboardServicePartnerRequest.class)),
                mapper::writeValueAsString);
    }
}
