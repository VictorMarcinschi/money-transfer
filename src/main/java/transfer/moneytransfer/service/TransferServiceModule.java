package transfer.moneytransfer.service;

import dagger.Module;
import dagger.Provides;
import transfer.moneytransfer.repository.TransferRepository;

import java.time.Clock;
import java.util.UUID;

@Module
public class TransferServiceModule {

    @Provides
    static TransferService provideTransferService(TransferRepository transferRepository, Clock systemClock) {
        return new TransferService(transferRepository, () -> UUID.randomUUID().toString(), systemClock);
    }
}
