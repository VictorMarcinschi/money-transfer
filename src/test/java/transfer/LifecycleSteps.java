package transfer;

import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.annotations.BeforeStories;
import spark.Spark;
import transfer.config.DaggerSystemClockFactory;

public class LifecycleSteps {

    @BeforeStories
    public void startApplication() {
        DaggerMoneyTransferAppFactory.builder()
                .moneyTransferAppModule(new MoneyTransferAppModule("application.properties"))
                .systemClockFactory(DaggerSystemClockFactory.create())
                .build()
                .app()
                .start();

        Spark.awaitInitialization();
    }

    @AfterStories
    public void stopApplication() {
        Spark.stop();
        Spark.awaitStop();
    }
}
