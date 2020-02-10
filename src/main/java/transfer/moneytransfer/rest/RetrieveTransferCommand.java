package transfer.moneytransfer.rest;

import java.time.Clock;

import lombok.RequiredArgsConstructor;
import transfer.domain.Command;
import transfer.domain.CommandResult;
import transfer.moneytransfer.service.TransferService;
import transfer.partner.repository.ServicePartnerRepository;
import transfer.validation.ValidationResult;

@RequiredArgsConstructor
class RetrieveTransferCommand implements Command<RetrieveTransferRequest, RetrieveTransferResponse> {

    private final ServicePartnerRepository servicePartnerRepository;
    private final TransferService transferService;
    private final Clock systemClock;

    @Override
    public CommandResult<RetrieveTransferResponse> execute(RetrieveTransferRequest request) {
        var partner = servicePartnerRepository.findByIdentifier(request.getPartnerIdentifier()).get();
        var retrieval = transferService.retrieveTransfer(request.getTransferIdentifier(), partner);

        return CommandResult.<RetrieveTransferResponse>builder()
                .validationResult(new ValidationResult())
                .value(new RetrieveTransferResponse(retrieval, systemClock.getZone()))
                .build();
    }
}
