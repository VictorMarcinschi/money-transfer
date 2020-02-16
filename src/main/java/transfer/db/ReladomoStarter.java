package transfer.db;

import com.gs.fw.common.mithra.MithraManager;
import com.gs.fw.common.mithra.MithraManagerProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Clock;

@Slf4j
@RequiredArgsConstructor
public class ReladomoStarter {

    private final String connectionString;
    private final String user;
    private final String password;
    private final String configLocation;
    private final int transactionTimeout;
    private final Clock systemClock;

    public void start() {
        H2ConnectionManager.getInstance().init(connectionString, user, password, systemClock.getZone());

        MithraManager mithra = MithraManagerProvider.getMithraManager();
        mithra.setTransactionTimeout(transactionTimeout);

        try (var is = getClass().getResourceAsStream(configLocation)) {
            MithraManagerProvider.getMithraManager().readConfiguration(is);

            log.info("Initialized Reladomo from config {}", configLocation);
        } catch (Exception e) {
            log.error("Could not initialize Reladomo. Exiting...");
            if (log.isDebugEnabled()) {
                log.debug("Failed to read Reladomo configuration file", e);
            }
            System.exit(1);
        }
    }
}
