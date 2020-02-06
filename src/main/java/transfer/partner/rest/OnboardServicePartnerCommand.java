package transfer.partner.rest;

import transfer.rest.Command;
import transfer.rest.CommandResult;

class OnboardServicePartnerCommand implements Command<NewServicePartnerRequest, String> {

    @Override
    public CommandResult<String> execute(NewServicePartnerRequest request) {
        return null;
    }
}
