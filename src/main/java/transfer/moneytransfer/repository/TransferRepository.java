package transfer.moneytransfer.repository;

import transfer.moneytransfer.model.Transfer;

public interface TransferRepository {

    void create(Transfer transfer);
}
