-- create Contacts schema
create schema if not exists contacts;

CREATE SEQUENCE contacts.hibernate_sequence START WITH 3 INCREMENT BY 1 NO CYCLE;

-- user_profile
create table if not exists contacts.contact (
   id INTEGER primary key,
   name varchar(255) not null,
   email varchar(255) not null unique,
   profession varchar(255)
);

create index contact_user_email_id on contacts.contact (email);