package transfer.moneytransfer.repository;

import transfer.moneytransfer.model.Transfer;

import java.util.Optional;

public interface TransferRepository {

    Optional<Transfer> findByIdentifier(String identifier);

    void create(Transfer transfer);
}
