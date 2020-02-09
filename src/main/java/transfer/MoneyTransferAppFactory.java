package transfer;

import dagger.Component;
import transfer.config.SystemClockFactory;

import javax.inject.Singleton;

@Singleton
@Component(modules = {MoneyTransferAppModule.class}, dependencies = SystemClockFactory.class)
interface MoneyTransferAppFactory {

    MoneyTransferApp app();
}
