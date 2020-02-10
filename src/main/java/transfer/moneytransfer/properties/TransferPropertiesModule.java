package transfer.moneytransfer.properties;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import transfer.config.properties.PropertiesUtil;
import transfer.moneytransfer.model.UserAttribute;

import javax.inject.Named;
import java.util.Properties;

import static transfer.moneytransfer.properties.TransferProperties.CURRENCIES_SUPPORTED;
import static transfer.moneytransfer.properties.TransferProperties.RETRIEVAL_SCAN_JOB_INTERVAL;
import static transfer.moneytransfer.properties.TransferProperties.RETRIEVAL_VALIDITY_CONFIG;
import static transfer.moneytransfer.properties.TransferProperties.VALIDITY_RETRIEVAL_EMAIL;
import static transfer.moneytransfer.properties.TransferProperties.VALIDITY_RETRIEVAL_PHONE;
import static transfer.moneytransfer.properties.TransferProperties.VALIDITY_TRANSFER_MAX;
import static transfer.moneytransfer.properties.TransferProperties.VALIDITY_TRANSFER_MIN;

@Module
public class TransferPropertiesModule {

    private static final String TRANSFER_PROPERTIES = "transfers.properties";

    @Provides
    @Named(TRANSFER_PROPERTIES)
    static Properties provideApplicationProperties() {
        return PropertiesUtil.readProperties(TransferPropertiesModule.class.getClassLoader(), TRANSFER_PROPERTIES);
    }

    @Provides
    @Named(VALIDITY_TRANSFER_MAX)
    static int provideMaxTransferValidity(@Named(TRANSFER_PROPERTIES) Properties properties) {
        return Integer.valueOf(properties.getProperty(VALIDITY_TRANSFER_MAX));
    }

    @Provides
    @Named(VALIDITY_TRANSFER_MIN)
    static int provideMinTransferValidity(@Named(TRANSFER_PROPERTIES) Properties properties) {
        return Integer.valueOf(properties.getProperty(VALIDITY_TRANSFER_MIN));
    }

    @Provides
    @IntoMap
    @StringKey("PHONE")
    @Named(RETRIEVAL_VALIDITY_CONFIG)
    static Integer providePhoneRetrievalCodeValidity(@Named(TRANSFER_PROPERTIES) Properties properties) {
        return Integer.valueOf(properties.getProperty(VALIDITY_RETRIEVAL_PHONE));
    }

    @Provides
    @IntoMap
    @StringKey("EMAIL")
    @Named(RETRIEVAL_VALIDITY_CONFIG)
    static Integer provideEmailRetrievalCodeValidity(@Named(TRANSFER_PROPERTIES) Properties properties) {
        return Integer.valueOf(properties.getProperty(VALIDITY_RETRIEVAL_EMAIL));
    }

    @Provides
    @Named(RETRIEVAL_SCAN_JOB_INTERVAL)
    static int provideRetrievalCodeExpiryScanJobInterval(@Named(TRANSFER_PROPERTIES) Properties properties) {
        return Integer.valueOf(properties.getProperty(RETRIEVAL_SCAN_JOB_INTERVAL));
    }

    @Provides
    @Named(CURRENCIES_SUPPORTED)
    static String[] provideSupportedCurrencies(@Named(TRANSFER_PROPERTIES) Properties properties) {
        return properties.getProperty(CURRENCIES_SUPPORTED).split(",");
    }
}
