package transfer.moneytransfer.repository;

import transfer.moneytransfer.model.Transfer;
import transfer.moneytransfer.model.TransferFinder;

import java.util.Optional;

public class ReladomoTransferRepository implements TransferRepository {

    @Override
    public Optional<Transfer> findByIdentifier(String identifier) {
        return Optional.ofNullable(TransferFinder.findByUqTransfer(identifier));
    }

    @Override
    public void create(Transfer transfer) {
        transfer.cascadeInsert();
    }
}
