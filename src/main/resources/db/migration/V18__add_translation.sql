drop table if exists temp_translation;

create table temp_translation (
                                  lang varchar(3),
                                  language_key text,
                                  value text
);


insert into temp_translation (lang, language_key, value)
values ('en', 'message.city.not.exist', 'City not found');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.city.not.exist', 'Город не найден');



insert into temp_translation (lang, language_key, value)
values ('en', 'message.organization.not.exist', 'Organization not found');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.organization.not.exist', 'Организация не найдена');




insert into temp_translation (lang, language_key, value)
values ('en', 'message.contract.not.exist', 'Contract not found');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.contract.not.exist', 'Контракт не найден');


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
