package transfer.partner.rest;

import java.time.Clock;

import lombok.RequiredArgsConstructor;
import transfer.domain.Command;
import transfer.rest.RestResponse;

@RequiredArgsConstructor
class ServicePartnerController {

    private final Command<RegisterServicePartnerRequest, String> onboardPartnerCommand;
    private final Clock systemClock;

    RestResponse registerServicePartner(RegisterServicePartnerRequest partnerRequest) {
        var result = onboardPartnerCommand.execute(partnerRequest, systemClock);
        return RestResponse.from(result, 201);
    }
}
