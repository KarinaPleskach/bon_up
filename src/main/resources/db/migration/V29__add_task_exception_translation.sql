drop table if exists temp_translation;

create table temp_translation (
    lang varchar(3),
    language_key text,
    value text
);


insert into temp_translation (lang, language_key, value)
values ('en', 'TaskAlreadyRejectedException', 'Task already rejected');

insert into temp_translation (lang, language_key, value)
values ('ru', 'TaskAlreadyRejectedException', 'Задание уже отклонено');


insert into temp_translation (lang, language_key, value)
values ('en', 'TaskAlreadyAcceptedException', 'Task already accepted');

insert into temp_translation (lang, language_key, value)
values ('ru', 'TaskAlreadyAcceptedException', 'Задание уже принято');


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