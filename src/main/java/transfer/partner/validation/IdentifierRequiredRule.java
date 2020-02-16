package transfer.partner.validation;

import transfer.partner.rest.RegisterServicePartnerRequest;
import transfer.validation.rules.NonBlankFieldRule;

class IdentifierRequiredRule extends NonBlankFieldRule<RegisterServicePartnerRequest> {

    IdentifierRequiredRule() {
        super(400, RegisterServicePartnerRequest::getIdentifier, "Service partner identifier");
    }
}
