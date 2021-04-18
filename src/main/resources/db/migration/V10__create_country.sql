create table country (
    id bigserial primary key ,
    language_key character varying(255) not null,
    photo_id bigint,
    currency_id bigint not null,
    constraint country_lang_key_id_uq unique (language_key),
    constraint country_to_currency_fk foreign key (currency_id) references currency (id)
);

insert into country (language_key, photo_id, currency_id) values
    ('country.BYN', 2, 1),
    ('country.RUB', 1, 2),
    ('country.USD', 3, 3);
