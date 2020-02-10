package transfer.moneytransfer.service;

import transfer.moneytransfer.model.Transfer;

interface RetrievalCodeGenerator {

    String generateFor(Transfer transfer);
}
