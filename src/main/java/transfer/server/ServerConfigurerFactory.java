package transfer.server;

import dagger.Component;

@Component(modules = ServerConfigurerModule.class)
public interface ServerConfigurerFactory {

    ServerConfigurer configurer();
}
