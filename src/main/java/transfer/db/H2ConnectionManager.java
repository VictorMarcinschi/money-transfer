package transfer.db;

import com.gs.fw.common.mithra.bulkloader.BulkLoader;
import com.gs.fw.common.mithra.connectionmanager.SourcelessConnectionManager;
import com.gs.fw.common.mithra.connectionmanager.XAConnectionManager;
import com.gs.fw.common.mithra.databasetype.DatabaseType;
import com.gs.fw.common.mithra.databasetype.H2DatabaseType;

import java.sql.Connection;
import java.util.TimeZone;

public class H2ConnectionManager implements SourcelessConnectionManager {

    private static H2ConnectionManager instance;

    private final XAConnectionManager xaConnectionManager;

    public static synchronized H2ConnectionManager getInstance() {
        if (instance == null) {
            instance = new H2ConnectionManager();
        }
        return instance;
    }

    private H2ConnectionManager() {
        xaConnectionManager = new XAConnectionManager();
    }

    public void init(String connectionString, String user, String password) {
        xaConnectionManager.setDriverClassName("org.h2.Driver");
        xaConnectionManager.setJdbcConnectionString(connectionString);
        xaConnectionManager.setJdbcUser(user);
        xaConnectionManager.setJdbcPassword(password);
        xaConnectionManager.setPoolName("H2DB Connection Pool");
        xaConnectionManager.setInitialSize(1);
        xaConnectionManager.setPoolSize(10);
        xaConnectionManager.initialisePool();
    }

    @Override
    public BulkLoader createBulkLoader() {
        throw new UnsupportedOperationException("BulkLoader not supported");
    }

    @Override
    public Connection getConnection() {
        return xaConnectionManager.getConnection();
    }

    @Override
    public DatabaseType getDatabaseType() {
        return H2DatabaseType.getInstance();
    }

    @Override
    public TimeZone getDatabaseTimeZone() {
        return TimeZone.getDefault();
    }

    @Override
    public String getDatabaseIdentifier() {
        var connectStringSplit = xaConnectionManager.getJdbcConnectionString().split(":");
        return connectStringSplit[connectStringSplit.length - 1];
    }
}
