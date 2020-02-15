package transfer.moneytransfer.rest;

import lombok.RequiredArgsConstructor;
import transfer.domain.Command;
import transfer.domain.CommandResult;
import transfer.moneytransfer.service.TransferService;
import transfer.partner.model.ServicePartner;
import transfer.partner.repository.ServicePartnerRepository;
import transfer.user.model.User;
import transfer.user.repository.UserRepository;
import transfer.user.service.UserService;
import transfer.validation.ValidationResult;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

@RequiredArgsConstructor
class SubmitTransferCommand implements Command<SubmitTransferRequest, SubmitTransferResponse> {

    private final ServicePartnerRepository partnerRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final TransferService transferService;

    @Override
    public CommandResult<SubmitTransferResponse> execute(SubmitTransferRequest request, Clock systemClock) {
        var partner = resolveServicePartner(request);
        var sender = resolveSender(request);
        var receiver = resolveReceiver(request);

        var transfer = transferService.submitTransfer(partner, sender, receiver, request.getReceiverAttribute(),
                request.extractFunds(), request.getRetrievalExpiry());

        var response = SubmitTransferResponse.builder()
                .senderIdentifier(sender.getUUIDIdentifier())
                .transferIdentifier(transfer.getIdentifier())
                .retrievalDueBy(LocalDate.ofInstant(
                        Instant.ofEpochMilli(transfer.getRetrievalExpiry().getTime()),
                        systemClock.getZone()))
                .sendNotificationVia(transfer.receiverAttribute())
                .sendNotificationTo(transfer.receiver())
                .build();

        return CommandResult.<SubmitTransferResponse>builder()
                .value(response)
                .build();
    }

    private ServicePartner resolveServicePartner(SubmitTransferRequest request) {
        return partnerRepository.findByIdentifier(request.getPartnerIdentifier())
                .orElseThrow(() -> new IllegalArgumentException("Service partner not found"));
    }

    private User resolveSender(SubmitTransferRequest request) {
        return Optional.ofNullable(request.getSenderIdentifier()).map(
                id -> userRepository.findByIdentifier(id)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid sender identifier")))
                .orElse(userService.resolveUserByDetails(request.getSenderDetails().getEmail(),
                        request.getSenderDetails().getPhone()));
    }

    private User resolveReceiver(SubmitTransferRequest request) {
        return userService.resolveUserByAttribute(request.getReceiverAttribute(),
                request.getReceiver());
    }
}
