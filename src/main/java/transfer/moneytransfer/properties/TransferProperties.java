package transfer.moneytransfer.properties;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TransferProperties {

    public static final String VALIDITY_TRANSFER_MAX = "transfers.retrieval.validity.days.max";
    public static final String VALIDITY_TRANSFER_MIN = "transfers.retrieval.validity.days.min";
    public static final String VALIDITY_RETRIEVAL_PHONE = "transfers.retrieval.code.phone.validity.mins";
    public static final String VALIDITY_RETRIEVAL_EMAIL = "transfers.retrieval.code.email.validity.mins";
    public static final String RETRIEVAL_SCAN_JOB_INTERVAL = "transfers.retrieval.code.scan.interval.seconds";
    public static final String CURRENCIES_SUPPORTED = "transfers.currencies.supported";

    public static final String RETRIEVAL_VALIDITY_CONFIG = "transfers.retrieval.code.validity";
}
