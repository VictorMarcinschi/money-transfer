create table transfers (
    id bigserial primary key,
    identifier varchar(36) not null,
    sending_service_partner_id int not null,
    sending_user_id int not null,
    receiving_user_id int not null,
    receiver_attr varchar(20) not null,
    currency_code varchar(10) not null,
    amount numeric(13, 5) not null,
    status varchar(20) not null,
    retrieval_expiry date not null,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now(),

    constraint uq_transfers
        unique (identifier),

    constraint fk_transfers_service_partners
        foreign key (sending_service_partner_id)
            references service_partners (id),

    constraint fk_transfers_users_sending_user
        foreign key (sending_user_id)
            references users (id),

    constraint fk_transfers_users_receiving_user
            foreign key (receiving_user_id)
                references users (id)
);

create table retrievals (
    id bigserial primary key,
    transfer_id bigint not null,
    retrieving_service_partner_id int not null,
    status varchar(20) not null,
    confirmation_code char(6) not null,
    confirmation_expiry timestamp not null,
    confirmed_at timestamp,
    created_at timestamp not null,
    updated_at timestamp not null,

    constraint uq_retrievals
        unique (transfer_id, confirmation_code),

    constraint fk_retrievals_transfers
        foreign key (transfer_id)
            references transfers (id),

    constraint fk_retrievals_service_partners
        foreign key (retrieving_service_partner_id)
            references service_partners (id)
);

create index idx_retrievals_transfer_partner
    on retrievals (transfer_id, retrieving_service_partner_id);