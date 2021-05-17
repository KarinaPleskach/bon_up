insert into category (id, key, parent_id) values
(50,'food', null),
(51, 'sport', null),
(52, 'media', null),
(53, 'health', null),
(54, 'literature', null),
(55, 'films', null),
(56, 'music', null),
(54, 'coffee', null),
(55, 'services', null);


drop table if exists temp_translation;

create table temp_translation (
    lang varchar(3),
    language_key text,
    value text
);

insert into temp_translation (lang, language_key, value)
values ('en', 'food', 'Food'),
       ('en', 'sport', 'Sport/Tourism'),
       ('en', 'media', 'Media'),
       ('en', 'health', 'Health'),
       ('en', 'literature', 'Literature'),
       ('en', 'films', 'Films'),
       ('en', 'music', 'Music'),
       ('en', 'coffee', 'Coffee'),
       ('en', 'services', 'Services');


insert into temp_translation (lang, language_key, value)
values ('ru', 'food', 'Еда'),
       ('ru', 'sport', 'Спорт/Туризм'),
       ('ru', 'media', 'Медиа'),
       ('ru', 'health', 'Здоровье'),
       ('ru', 'literature', 'Литература'),
       ('ru', 'films', 'Фильмы'),
       ('ru', 'music', 'Музыка'),
       ('ru', 'coffee', 'Кофе'),
       ('ru', 'services', 'Сервисы');

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