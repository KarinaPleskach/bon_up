create table currency (
    id bigserial primary key,
    reduction character varying(3) not null,
    language_key character varying(255) not null,
    symbol character varying(4) not null,
    constraint currency_reduction_uq unique (reduction),
    constraint currency_lang_key_id_uq unique (language_key)
);

drop table if exists temp_translation;

create table temp_translation (
    lang varchar(3),
    language_key text,
    value text
);

insert into temp_translation (lang, language_key, value)
values ('en', 'currency.BYN', 'Belarusian ruble');

insert into temp_translation (lang, language_key, value)
values ('ru', 'currency.BYN', 'Белорусский рубль');


insert into temp_translation (lang, language_key, value)
values ('en', 'currency.RUB', 'Russian ruble');

insert into temp_translation (lang, language_key, value)
values ('ru', 'currency.RUB', 'Российский рубль');


insert into temp_translation (lang, language_key, value)
values ('en', 'currency.USD', 'American dollar');

insert into temp_translation (lang, language_key, value)
values ('ru', 'currency.USD', 'Американский доллар');


insert into temp_translation (lang, language_key, value)
values ('en', 'country.BYN', 'Belarus');

insert into temp_translation (lang, language_key, value)
values ('ru', 'country.BYN', 'Беларусь');


insert into temp_translation (lang, language_key, value)
values ('en', 'country.RUB', 'Russia');

insert into temp_translation (lang, language_key, value)
values ('ru', 'country.RUB', 'Россия');


insert into temp_translation (lang, language_key, value)
values ('en', 'country.USD', 'USA');

insert into temp_translation (lang, language_key, value)
values ('ru', 'country.USD', 'США');


insert into temp_translation (lang, language_key, value)
values ('en', 'country.BYN.Minsk', 'Minsk');

insert into temp_translation (lang, language_key, value)
values ('ru', 'country.BYN.Minsk', 'Минск');


insert into language_key (key)
select language_key
from temp_translation
where lang = 'en';

insert into language_translation (value, key_id, language_id)
select
    value,
    language_key.id,
    (select id
     from language
     where lang = temp_translation.lang)
from temp_translation
         join language_key on temp_translation.language_key = language_key.key;

drop table temp_translation;


insert into currency (reduction, language_key, symbol) values
            ('BYN', 'currency.BYN', '฿'),
            ('RUB', 'currency.RUB', '₽'),
            ('USD', 'currency.USD', '$');


