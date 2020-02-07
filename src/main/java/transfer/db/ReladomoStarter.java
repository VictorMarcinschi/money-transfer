package transfer.db;

import com.gs.fw.common.mithra.MithraManager;
import com.gs.fw.common.mithra.MithraManagerProvider;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
@RequiredArgsConstructor
public class ReladomoStarter {

    private static final String RUNTIME_CONFIG_LOCATION = "/reladomo/config/ReladomoRuntimeConfig.xml";
    private static final int TRANSACTION_TIMEOUT = 30;

    private final String connectionString;
    private final String user;
    private final String password;

    public void start() {
        H2ConnectionManager.getInstance().init(connectionString, user, password);

        MithraManager mithra = MithraManagerProvider.getMithraManager();
        mithra.setTransactionTimeout(TRANSACTION_TIMEOUT);

        try (var is = Files.newInputStream(Paths.get(getClass().getResource(RUNTIME_CONFIG_LOCATION).toURI()))) {
            MithraManagerProvider.getMithraManager().readConfiguration(is);

            log.info("Initialized Reladomo from config {}", RUNTIME_CONFIG_LOCATION);
        } catch (URISyntaxException | IOException e) {
            log.error("Could not initialize Reladomo. Exiting...");
            if (log.isDebugEnabled()) {
                log.debug("Failed to read Reladomo configuration file", e);
            }
            System.exit(1);
        }
    }
}
