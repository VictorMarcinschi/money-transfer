package transfer.partner.repository;

import transfer.partner.model.ServicePartner;

class ReladomoServicePartnerRepository implements ServicePartnerRepository {

    @Override
    public void create(ServicePartner partner) {
        partner.insert();
    }
}
