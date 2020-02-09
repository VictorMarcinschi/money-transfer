package transfer.config;

import dagger.Component;

import java.time.Clock;

@Component(modules = ClockModule.class)
public interface SystemClockFactory {

    Clock systemClock();
}
