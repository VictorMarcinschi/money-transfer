alter table service_partners
    alter column identifier char(10) not null;

alter table service_partners
    add column created_at timestamp not null default now();

alter table service_partners
    add column updated_at timestamp not null default now();