package transfer.moneytransfer.repository;

import dagger.Module;
import dagger.Provides;

@Module
public class TransferRepositoryModule {

    @Provides
    static TransferRepository provideTransferRepository() {
        return new ReladomoTransferRepository();
    }
}
