create extension if not exists "uuid-ossp";

create table if not exists photos
(
    photo_id              UUID unique        not null default uuid_generate_v1(),
    country_id            UUID not null               default uuid_generate_v1(),
    username              varchar(50) not null,
    photo                 bytea,
    description           varchar(225),

    primary key (photo_id)
    );

alter table photos
    owner to postgres;
