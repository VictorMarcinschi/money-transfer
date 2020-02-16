package transfer.partner.validation;

import transfer.partner.rest.RegisterServicePartnerRequest;
import transfer.validation.rules.NonBlankFieldRule;

class ApiBasePathRequiredRule extends NonBlankFieldRule<RegisterServicePartnerRequest> {

    ApiBasePathRequiredRule() {
        super(400, RegisterServicePartnerRequest::getApiBasePath, "Service partner API base path");
    }
}
