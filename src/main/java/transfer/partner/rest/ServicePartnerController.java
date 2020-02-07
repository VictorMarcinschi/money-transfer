package transfer.partner.rest;

import lombok.RequiredArgsConstructor;
import spark.Request;
import transfer.domain.Command;
import transfer.rest.RestResponse;

@RequiredArgsConstructor
class ServicePartnerController {

    private final Command<OnboardServicePartnerRequest, String> onboardPartnerCommand;

    RestResponse onboardServicePartner(OnboardServicePartnerRequest partnerRequest, Request request) {
        var result = onboardPartnerCommand.execute(partnerRequest);

        var builder = RestResponse.builder();
        if (result.isSuccessful()) {
            return builder
                    .status(201)
                    .location(request.matchedPath() + "/" + partnerRequest.getIdentifier())
                    .body(result.getValue())
                    .build();
        }

        var error = result.getError();
        return builder
                .status(error.httpStatus())
                .body(error.message())
                .build();
    }
}
