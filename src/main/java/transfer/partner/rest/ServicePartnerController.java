package transfer.partner.rest;

import spark.Request;
import transfer.rest.RestResponse;

class ServicePartnerController {

    RestResponse onboardServicePartner(NewServicePartnerRequest partnerRequest, Request request) {
        return RestResponse.builder()
                .status(201)
                .location(request.matchedPath() + "/" + partnerRequest.getIdentifier())
                .body(partnerRequest)
                .build();
    }
}
