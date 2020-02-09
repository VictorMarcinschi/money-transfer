package transfer.partner.rest;

import lombok.RequiredArgsConstructor;
import transfer.domain.Command;
import transfer.rest.RestResponse;

@RequiredArgsConstructor
class ServicePartnerController {

    private final Command<RegisterServicePartnerRequest, String> onboardPartnerCommand;

    RestResponse registerServicePartner(RegisterServicePartnerRequest partnerRequest) {
        var result = onboardPartnerCommand.execute(partnerRequest);
        return RestResponse.builderFrom(result, 201).build();
    }
}
