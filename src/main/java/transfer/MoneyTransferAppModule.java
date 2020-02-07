package transfer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import lombok.RequiredArgsConstructor;
import transfer.config.properties.ApplicationPropertiesModule;
import transfer.db.DatabaseTools;
import transfer.server.ServerConfigurerFactory;

@RequiredArgsConstructor
@Module(subcomponents = {DatabaseTools.class, ServerConfigurerFactory.class})
class MoneyTransferAppModule {

    private final String propertiesFileName;

    @Provides
    @Singleton
    MoneyTransferApp provideMoneyTransferApp(DatabaseTools.Builder dbToolsBuilder,
            ServerConfigurerFactory.Builder serverConfigurerBuilder) {

        var applicationProperties = new ApplicationPropertiesModule(propertiesFileName);

        var dbTools = dbToolsBuilder
                .applicationPropertiesModule(applicationProperties)
                .build();

        var configurer = serverConfigurerBuilder
                .applicationPropertiesModule(applicationProperties)
                .build()
                .configurer();

        return new MoneyTransferApp(dbTools, configurer);
    }
}
