alter table users
    drop constraint uq_users_email;

alter table users
    drop column email;

alter table users
    add column identifier char(36) not null;

alter table users
    add constraint uq_users_identifier
        unique (identifier);

alter table users
    add column confirmed boolean not null;

alter table users
    add column confirming_partner_id int not null;

alter table users
    add constraint fk_users_service_partners
        foreign key (confirming_partner_id)
            references service_partners (id);

alter table users
    add column created_at timestamp not null default now();

alter table users
    add column updated_at timestamp not null default now();

create table user_details (
    user_id int primary key,
    email varchar(255),
    phone varchar(25),
    created_at timestamp not null default now(),
    updated_at timestamp not null default now(),

    constraint uq_user_details_email
        unique (email),

    constraint uq_user_details_phone
        unique (phone),

    constraint fk_user_details_users
        foreign key (user_id)
            references users(id),

    constraint chk_user_details_email_or_phone
        check email is not null or phone is not null
);