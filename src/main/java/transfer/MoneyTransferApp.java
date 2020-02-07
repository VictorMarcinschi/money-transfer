package transfer;

import lombok.RequiredArgsConstructor;
import transfer.db.DatabaseTools;
import transfer.server.ServerConfigurer;

@RequiredArgsConstructor
public class MoneyTransferApp {

    private final DatabaseTools databaseTools;
    private final ServerConfigurer serverConfigurer;

    public static void main(String[] args) {
        MoneyTransferApp app = DaggerMoneyTransferAppFactory.builder()
                .moneyTransferAppModule(new MoneyTransferAppModule("application.properties"))
                .build()
                .app();

        app.start();
    }

    void start() {
        databaseTools.migration().start();
        databaseTools.reladomo().start();
        serverConfigurer.configure();
    }
}
