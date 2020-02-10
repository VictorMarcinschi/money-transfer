package transfer.moneytransfer.service;

import lombok.RequiredArgsConstructor;
import transfer.moneytransfer.model.Transfer;

import java.security.SecureRandom;

@RequiredArgsConstructor
class SecureRandomIntGenerator implements RetrievalCodeGenerator {

    private final SecureRandom random;
    private final int bound;

    @Override
    public String generateFor(Transfer transfer) {
        return String.valueOf(random.nextInt(bound));
    }
}
