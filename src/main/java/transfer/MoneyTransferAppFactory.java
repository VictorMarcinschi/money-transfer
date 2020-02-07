package transfer;

import javax.inject.Singleton;

import dagger.Component;
import transfer.config.ClockModule;

@Singleton
@Component(modules = {MoneyTransferAppModule.class, ClockModule.class})
interface MoneyTransferAppFactory {

    MoneyTransferApp app();
}
