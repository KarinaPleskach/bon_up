
drop table if exists temp_translation;

create table temp_translation (
                                  lang varchar(3),
                                  language_key text,
                                  value text
);

insert into temp_translation (lang, language_key, value)
values ('en', 'goal.1', '1000 points');

insert into temp_translation (lang, language_key, value)
values ('ru', 'goal.1', '1000 баллов');

insert into temp_translation (lang, language_key, value)
values ('en', 'goal.2', 'No more 1000 points');

insert into temp_translation (lang, language_key, value)
values ('ru', 'goal.2', 'Нет уже 1000 баллов');

insert into temp_translation (lang, language_key, value)
values ('en', 'goal.3', '100 tasks');

insert into temp_translation (lang, language_key, value)
values ('ru', 'goal.3', '100 tasks');


insert into temp_translation (lang, language_key, value)
values ('en', 'goal.1.desc', 'Earned more than 1000 points for the entire period');

insert into temp_translation (lang, language_key, value)
values ('ru', 'goal.1.desc', 'Заработано более 1000 баллов за весь период');

insert into temp_translation (lang, language_key, value)
values ('en', 'goal.2.desc', 'Spent more than 1000 points for the entire period');

insert into temp_translation (lang, language_key, value)
values ('ru', 'goal.2.desc', 'Потрачено более 1000 баллов за весь период');

insert into temp_translation (lang, language_key, value)
values ('en', 'goal.3.desc', 'Completed more than 100 tasks');

insert into temp_translation (lang, language_key, value)
values ('ru', 'goal.3.desc', 'Выполнено более 100 заданий');

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
