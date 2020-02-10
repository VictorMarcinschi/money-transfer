package transfer.moneytransfer.rest;

import lombok.RequiredArgsConstructor;
import transfer.domain.Command;
import transfer.domain.CommandResult;
import transfer.moneytransfer.service.TransferService;
import transfer.partner.repository.ServicePartnerRepository;
import transfer.validation.ValidationResult;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.Optional;

@RequiredArgsConstructor
class ConfirmRetrievalCommand implements Command<ConfirmRetrievalRequest, ConfirmRetrievalResponse> {

    private final TransferService transferService;
    private final ServicePartnerRepository servicePartnerRepository;
    private final Clock systemClock;

    @Override
    public CommandResult<ConfirmRetrievalResponse> execute(ConfirmRetrievalRequest request) {
        var partner = servicePartnerRepository.findByIdentifier(request.getPartnerIdentifier()).get();
        var retrieval = transferService.confirmRetrieval(request.getTransferIdentifier(),
                request.getConfirmationCode(), partner);

        var transfer = retrieval.getTransfer();
        var receivingUser = transfer.getReceivingUser();
        if (Optional.ofNullable(request.getReceiverIdentifier()).isEmpty()) {
            var now = ZonedDateTime.now(systemClock);
            var receiverDetails = request.getReceiverDetails();
            receivingUser.updateDetails(receiverDetails, now);
        }

        var approval = ConfirmRetrievalResponse.RetrievalApproval.builder()
                .transferIdentifier(transfer.getIdentifier())
                .partnerIdentifier(partner.getIdentifier())
                .partnerApiBasePath(partner.getApiBasePath())
                .receiverIdentifier(receivingUser.getUUIDIdentifier())
                .confirmedAt(retrieval.confirmedAt(systemClock.getZone()))
                .confirmedVia(transfer.receiverAttribute())
                .currencyCode(transfer.getCurrencyCode())
                .amount(transfer.getAmount())
                .build();

        var response = ConfirmRetrievalResponse.builder()
                .receiverIdentifier(receivingUser.getUUIDIdentifier())
                .transferIdentifier(transfer.getIdentifier())
                .retrievalStatus(retrieval.status())
                .approval(approval)
                .build();

        return CommandResult.<ConfirmRetrievalResponse>builder()
                .validationResult(new ValidationResult())
                .value(response)
                .build();
    }
}
