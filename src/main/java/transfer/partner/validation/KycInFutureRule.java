package transfer.partner.validation;

import transfer.partner.rest.RegisterServicePartnerRequest;
import transfer.validation.rules.DateInFutureFieldRule;

import java.time.Clock;

class KycInFutureRule extends DateInFutureFieldRule<RegisterServicePartnerRequest> {

    KycInFutureRule(Clock clock) {
        super(400, req -> req.getKycExpiry().atStartOfDay() , "KYC expiry", clock);
    }
}
