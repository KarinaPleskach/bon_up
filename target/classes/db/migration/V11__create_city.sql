create table city (
    id bigserial primary key ,
    language_key character varying(255) not null,
    country_id bigint not null,
    constraint city_lang_key_id_uq unique (language_key),
    constraint city_to_country_fk foreign key (country_id) references country (id)
);

insert into city (language_key, country_id) values
    ('country.BYN.Minsk', 1);
