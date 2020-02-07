package transfer.partner.repository;

import dagger.Module;
import dagger.Provides;

@Module
public class ServicePartnerRepositoryModule {

    @Provides
    static ServicePartnerRepository provideServicePartnerRepository() {
        return new ReladomoServicePartnerRepository();
    }
}
