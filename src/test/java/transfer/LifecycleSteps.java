package transfer;

import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.annotations.BeforeStories;
import spark.Spark;
import transfer.config.DaggerSystemClockFactory;

import javax.sql.DataSource;

public class LifecycleSteps {

    private DataSource dbDataSource;

    @BeforeStories
    public void startApplication() {
        var appFactory = DaggerMoneyTransferAppFactory.builder()
                .moneyTransferAppModule(new MoneyTransferAppModule("application.properties"))
                .systemClockFactory(DaggerSystemClockFactory.create())
                .build();

        appFactory.app().start();
        Spark.awaitInitialization();
    }

    @AfterStories
    public void stopApplication() {
        Spark.stop();
        Spark.awaitStop();
    }
}
