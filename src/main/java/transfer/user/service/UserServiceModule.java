package transfer.user.service;

import dagger.Module;
import dagger.Provides;
import transfer.user.repository.UserRepository;
import transfer.user.repository.UserRepositoryModule;

import java.time.Clock;
import java.util.UUID;

@Module(includes = UserRepositoryModule.class)
public class UserServiceModule {

    @Provides
    static UserService provideUserService(UserRepository userRepository, Clock systemClock) {
        return new UserService(userRepository, UUID::randomUUID, systemClock);
    }
}
