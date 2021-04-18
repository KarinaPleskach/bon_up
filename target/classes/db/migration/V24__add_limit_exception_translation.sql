drop table if exists temp_translation;

create table temp_translation (
                                  lang varchar(3),
                                  language_key text,
                                  value text
);


insert into temp_translation (lang, language_key, value)
values ('en', 'NumberOfStocksLimitException', 'Number of stocks is out of limit');

insert into temp_translation (lang, language_key, value)
values ('ru', 'NumberOfStocksLimitException', 'Лимит акций исчерпан');


insert into temp_translation (lang, language_key, value)
values ('en', 'NumberOfMediumTasksException', 'Number of medium tasks is out of limit');

insert into temp_translation (lang, language_key, value)
values ('ru', 'NumberOfMediumTasksException', 'Лимит средних задач исчерпан');


insert into temp_translation (lang, language_key, value)
values ('en', 'NumberOfMediumCouponsException', 'Number of medium coupons is out of limit');

insert into temp_translation (lang, language_key, value)
values ('ru', 'NumberOfMediumCouponsException', 'Лимит средних купонов исчерпан');


insert into temp_translation (lang, language_key, value)
values ('en', 'NumberOfHeavyTasksException', 'Number of heavy tasks is out of limit');

insert into temp_translation (lang, language_key, value)
values ('ru', 'NumberOfHeavyTasksException', 'Лимит сложных задач исчерпан');


insert into temp_translation (lang, language_key, value)
values ('en', 'NumberOfHeavyCouponsException', 'Number of heavy coupons is out of limit');

insert into temp_translation (lang, language_key, value)
values ('ru', 'NumberOfHeavyCouponsException', 'Лимит сложных купонов исчерпан');



insert into temp_translation (lang, language_key, value)
values ('en', 'NumberOfEasyTasksException', 'Number of easy tasks is out of limit');

insert into temp_translation (lang, language_key, value)
values ('ru', 'NumberOfEasyTasksException', 'Лимит легких задач исчерпан');


insert into temp_translation (lang, language_key, value)
values ('en', 'NumberOfEasyCouponsException', 'Number of easy coupons is out of limit');

insert into temp_translation (lang, language_key, value)
values ('ru', 'NumberOfEasyCouponsException', 'Лимит легких купонов исчерпан');



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