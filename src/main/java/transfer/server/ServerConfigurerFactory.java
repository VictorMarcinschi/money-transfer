package transfer.server;

import dagger.Subcomponent;
import transfer.config.properties.ApplicationPropertiesModule;

@Subcomponent(modules = ServerConfigurerModule.class)
public interface ServerConfigurerFactory {

    ServerConfigurer configurer();

    @Subcomponent.Builder
    interface Builder {

        Builder applicationPropertiesModule(ApplicationPropertiesModule module);

        ServerConfigurerFactory build();
    }
}
