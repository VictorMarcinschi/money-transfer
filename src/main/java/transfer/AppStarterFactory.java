package transfer;

import dagger.Component;

import javax.inject.Singleton;

@Component(modules = MoneyTransferAppModule.class)
@Singleton
interface AppStarterFactory {

    AppStarter starter();
}
