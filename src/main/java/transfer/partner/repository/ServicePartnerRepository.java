package transfer.partner.repository;

import transfer.partner.model.ServicePartner;

import java.util.Optional;

public interface ServicePartnerRepository {

    void create(ServicePartner partner);

    Optional<ServicePartner> findByIdentifier(String identifier);
}
