package transfer.moneytransfer.service;

import dagger.Module;
import dagger.Provides;
import lombok.SneakyThrows;
import transfer.moneytransfer.model.UserAttribute;
import transfer.moneytransfer.properties.TransferPropertiesModule;
import transfer.moneytransfer.repository.TransferRepository;
import transfer.moneytransfer.repository.TransferRepositoryModule;

import javax.inject.Named;
import java.security.SecureRandom;
import java.time.Clock;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static transfer.moneytransfer.properties.TransferProperties.RETRIEVAL_VALIDITY_CONFIG;

@Module(includes = {TransferPropertiesModule.class, TransferRepositoryModule.class})
public class TransferServiceModule {

    @Provides
    @SneakyThrows
    static TransferService provideTransferService(TransferRepository transferRepository, Clock systemClock,
            @Named(RETRIEVAL_VALIDITY_CONFIG) Map<String, Integer> retrievalValidity) {

        var retrievalValidityByAttr = retrievalValidity.entrySet().stream()
                .collect(Collectors.toMap(e -> UserAttribute.valueOf(e.getKey()), e -> e.getValue()));

        var retrievalCodeGenerator = new SecureRandomIntGenerator(SecureRandom.getInstanceStrong(), 1_000_000);
        return new TransferService(transferRepository, () -> UUID.randomUUID().toString(),
                retrievalCodeGenerator, systemClock, retrievalValidityByAttr);
    }
}
