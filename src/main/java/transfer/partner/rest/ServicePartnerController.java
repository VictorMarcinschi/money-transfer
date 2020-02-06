package transfer.partner.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import spark.Request;
import transfer.rest.RestResponse;

@RequiredArgsConstructor
@Slf4j
class ServicePartnerController {

    private final ObjectMapper objectMapper;

    @SneakyThrows
    RestResponse onboardServicePartner(Request request) {
        log.info("Received new service partner request\n{}", request.body());
        var partnerRequest = objectMapper.readValue(request.body(), NewServicePartnerRequest.class);
        return RestResponse.builder()
                .status(201)
                .location(request.matchedPath() + "/" + partnerRequest.getIdentifier())
                .body(partnerRequest)
                .build();
    }
}
