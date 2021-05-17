insert into category (id, key, parent_id) values
(50,'new_food', null),
(51, 'new_sport', null),
(52, 'new_media', null),
(53, 'new_health', null),
(54, 'new_literature', null),
(55, 'new_films', null),
(56, 'new_music', null),
(57, 'new_coffee', null),
(58, 'new_services', null);


drop table if exists temp_translation;

create table temp_translation (
    lang varchar(3),
    language_key text,
    value text
);

insert into temp_translation (lang, language_key, value)
values ('en', 'new_food', 'Food'),
       ('en', 'new_sport', 'Sport/Tourism'),
       ('en', 'new_media', 'Media'),
       ('en', 'new_health', 'Health'),
       ('en', 'new_literature', 'Literature'),
       ('en', 'new_films', 'Films'),
       ('en', 'new_music', 'Music'),
       ('en', 'new_coffee', 'Coffee'),
       ('en', 'new_services', 'Services');


insert into temp_translation (lang, language_key, value)
values ('ru', 'new_food', 'Еда'),
       ('ru', 'new_sport', 'Спорт/Туризм'),
       ('ru', 'new_media', 'Медиа'),
       ('ru', 'new_health', 'Здоровье'),
       ('ru', 'new_literature', 'Литература'),
       ('ru', 'new_films', 'Фильмы'),
       ('ru', 'new_music', 'Музыка'),
       ('ru', 'new_coffee', 'Кофе'),
       ('ru', 'new_services', 'Сервисы');

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