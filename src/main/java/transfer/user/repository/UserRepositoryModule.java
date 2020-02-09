package transfer.user.repository;

import dagger.Module;
import dagger.Provides;

@Module
public class UserRepositoryModule {

    @Provides
    static UserRepository provideUserRepository() {
        return new ReladomoUserRepository();
    }
}
