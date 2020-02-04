package transfer;

import lombok.SneakyThrows;
import transfer.properties.ApplicationPropertiesModule;

public class MoneyTransferApp {

    @SneakyThrows
    public static void main(String[] args) {
        var starter = DaggerAppStarterFactory.builder()
                .applicationPropertiesModule(new ApplicationPropertiesModule("application.properties"))
                .build()
                .starter();

        starter.start();
    }
}
