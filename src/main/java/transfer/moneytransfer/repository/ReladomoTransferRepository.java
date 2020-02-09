package transfer.moneytransfer.repository;

import transfer.moneytransfer.model.Transfer;

public class ReladomoTransferRepository implements TransferRepository {

    @Override
    public void create(Transfer transfer) {
        transfer.insert();
    }
}
