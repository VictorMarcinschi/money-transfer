package transfer.partner.repository;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import transfer.partner.model.ServicePartner;
import transfer.partner.model.ServicePartnerFinder;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
class ReladomoServicePartnerRepository implements ServicePartnerRepository {

    @Override
    public void create(ServicePartner partner) {
        partner.insert();
    }

    @Override
    public Optional<ServicePartner> findByIdentifier(String identifier) {
        return Optional.ofNullable(
                ServicePartnerFinder.findOne(ServicePartnerFinder.identifier().eq(identifier)));
    }
}
