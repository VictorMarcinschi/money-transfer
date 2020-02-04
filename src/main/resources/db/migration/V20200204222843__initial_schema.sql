create table users (
    id serial primary key,
    email varchar(255) not null,

    constraint uq_users_email
        unique (email)
);

create table service_partners (
    id serial primary key,
    identifier varchar(255) not null,
    kyc_expiry timestamp not null,
    api_base_path varchar(1024) not null,

    constraint uq_service_partners_identifier
        unique (identifier)
)