package transfer;

import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeStory;
import spark.Spark;
import transfer.config.DaggerSystemClockFactory;

public class LifecycleSteps {

    @BeforeStory
    public void startApplication() {
        DaggerMoneyTransferAppFactory.builder()
                .moneyTransferAppModule(new MoneyTransferAppModule("application.properties"))
                .systemClockFactory(DaggerSystemClockFactory.create())
                .build()
                .app()
                .start();

        Spark.awaitInitialization();
    }

    @AfterStory
    public void stopApplication() {
        Spark.stop();
        Spark.awaitStop();
    }
}
