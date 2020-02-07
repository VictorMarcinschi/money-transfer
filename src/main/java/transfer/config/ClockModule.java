package transfer.config;

import dagger.Module;
import dagger.Provides;

import java.time.Clock;

@Module
public class ClockModule {

    @Provides
    static Clock provideSystemClock() {
        return Clock.systemDefaultZone();
    }
}
