drop table if exists temp_translation;

create table temp_translation (
                                  lang varchar(3),
                                  language_key text,
                                  value text
);

insert into temp_translation (lang, language_key, value)
values ('en', 'message.noSuchOrganization', 'No such organization');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.noSuchOrganization', 'Такой организации не существует');


insert into temp_translation (lang, language_key, value)
values ('en', 'message.deleted', 'Deleted successfully');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.deleted', 'Удалено успешно');


insert into temp_translation (lang, language_key, value)
values ('en', 'message.photo.exist', 'Photo already exist');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.photo.exist', 'Фотография уже существует');


insert into temp_translation (lang, language_key, value)
values ('en', 'message.photo.not.exist', 'Photo not exist');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.photo.not.exist', 'Фотографии не существует');


insert into temp_translation (lang, language_key, value)
values ('en', 'tireFitting', 'tire fitting');
insert into temp_translation (lang, language_key, value)
values ('ru', 'tireFitting', 'шиномонтаж');

insert into temp_translation (lang, language_key, value)
values ('en', 'balancing', 'balancing');
insert into temp_translation (lang, language_key, value)
values ('ru', 'balancing', 'балансировка');

insert into temp_translation (lang, language_key, value)
values ('en', 'diskEditingAndRolling', 'disk editing and rolling');
insert into temp_translation (lang, language_key, value)
values ('ru', 'diskEditingAndRolling', 'правка и раскатка дисков');

insert into temp_translation (lang, language_key, value)
values ('en', 'repairOfPuncturesAndCuts', 'repair of punctures and cuts');
insert into temp_translation (lang, language_key, value)
values ('ru', 'repairOfPuncturesAndCuts', 'ремонт проколов и порезов');

insert into temp_translation (lang, language_key, value)
values ('en', 'tireStorage', 'tire storage');
insert into temp_translation (lang, language_key, value)
values ('ru', 'tireStorage', 'хранение шин');

insert into temp_translation (lang, language_key, value)
values ('en', 'vulcanization', 'vulcanization');
insert into temp_translation (lang, language_key, value)
values ('ru', 'vulcanization', 'вулканизация');

insert into temp_translation (lang, language_key, value)
values ('en', 'diskRepair', 'disk repair');
insert into temp_translation (lang, language_key, value)
values ('ru', 'diskRepair', 'ремонт дисков');

insert into temp_translation (lang, language_key, value)
values ('en', 'heavy', 'heavy');

insert into temp_translation (lang, language_key, value)
values ('ru', 'heavy', 'сложный');


insert into temp_translation (lang, language_key, value)
values ('en', 'medium', 'medium');

insert into temp_translation (lang, language_key, value)
values ('ru', 'medium', 'средний');


insert into temp_translation (lang, language_key, value)
values ('en', 'easy', 'easy');

insert into temp_translation (lang, language_key, value)
values ('ru', 'easy', 'легкий');

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
