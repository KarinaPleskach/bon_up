create table category (
    id bigserial primary key,
    key character varying(45) not null,
    parent_id bigint,
    constraint categories_key_uq unique (key),
    constraint categories_parents_fk foreign key (parent_id) references category (id)
);

insert into category (id, key, parent_id) values
    (1,'tireFitting', null),
    (2, 'balancing', 1),
    (3, 'diskEditingAndRolling', 1),
    (4, 'repairOfPuncturesAndCuts', 1),
    (5, 'tireStorage', 1),
    (6, 'vulcanization', 1),
    (7, 'diskRepair', 1);

drop table if exists temp_translation;

create table temp_translation (
    lang varchar(3),
    language_key text,
    value text
);

insert into temp_translation (lang, language_key, value)
values ('en', 'elseTireFitting', 'else'),
       ('en', 'message.no.such.category', 'No such category'),
       ('en', 'message.not.subcategory', 'Not subcategory'),
       ('en', 'NumberOfTasksLimitException', 'Task limit is exhausted'),
       ('en', 'NoSuchStockException', 'No such stock'),
       ('en', 'NegativaNumberException', 'Number must be not negative'),
       ('en', 'NotPositiveNumberException', 'Number must be positive'),
       ('en', 'NoSuchTypeException', 'No such type'),
       ('en', 'NoSuchTaskException', 'No such task'),
       ('en', 'TaskNotUpToDateException', 'Task is out of date'),
       ('en', 'TaskAlreadyDoneException', 'Task is already done'),
       ('en', 'NoSuchEmployeeException', 'No such employee'),
       ('en', 'NumberOfCouponsLimitException', 'Number of coupons is out of limit'),
       ('en', 'NoSuchCouponException', 'Coupon is not exist'),
       ('en', 'CouponAlreadyBoughtException', 'Coupon is already bought'),
       ('en', 'CouponNotUpToDateException', 'Coupon is out of date'),
       ('en', 'NotEnoughBallsException', 'Not enough points'),
       ('en', 'CouponAlreadyActivatedException', 'Coupon is already activated'),
       ('en', 'CouponNotBoughtException', 'Coupon must be bought at first');


insert into temp_translation (lang, language_key, value)
values ('ru', 'elseTireFitting', '????????????'),
       ('ru', 'message.no.such.category', '?????? ?????????? ??????????????????'),
       ('ru', 'message.not.subcategory', '?????? ???? ????????????????????????'),
       ('ru', 'NumberOfTasksLimitException', '?????????? ?????????????? ????????????????'),
       ('ru', 'NoSuchStockException', '?????? ?????????? ??????????'),
       ('ru', 'NegativaNumberException', '?????????? ???????????? ???????? ???? ????????????????????'),
       ('ru', 'NotPositiveNumberException', '?????????? ???????????? ???????? ??????????????????????????'),
       ('ru', 'NoSuchTypeException', '?????? ???????????? ????????'),
       ('ru', 'NoSuchTaskException', '?????? ???????????? ??????????????'),
       ('ru', 'TaskNotUpToDateException', '???????? ?????????????? ??????????'),
       ('ru', 'TaskAlreadyDoneException', '?????????????? ?????? ??????????????'),
       ('ru', 'NoSuchEmployeeException', '?????? ???????????? ????????????????????'),
       ('ru', 'NumberOfCouponsLimitException', '???????????????????? ?????????????? ??????????????'),
       ('ru', 'NoSuchCouponException', '?????????? ???? ????????????????????'),
       ('ru', 'CouponAlreadyBoughtException', '?????????? ?????? ????????????'),
       ('ru', 'CouponNotUpToDateException', '???????? ???????????? ??????????'),
       ('ru', 'NotEnoughBallsException', '???????????????????????? ????????????'),
       ('ru', 'CouponAlreadyActivatedException', '?????????? ?????? ??????????????????????'),
       ('ru', 'CouponNotBoughtException', '?????????? ?????????????? ???????????? ???????? ????????????');

insert into temp_translation (lang, language_key, value)
values ('en', 'food', 'food'),
       ('en', 'sushi', 'sushi'),
       ('en', 'pizza', 'pizza'),
       ('en', 'caffee', 'cafes and restaurants'),
       ('en', 'deserts', 'desserts'),
       ('en', 'cofe', 'coffee'),
       ('en', 'govno', 'vegetarian cuisine'),
       ('en', 'pasta', 'paste'),
       ('en', 'shaverma', 'shawarma'),
       ('en', 'shashlyk', 'kebabs'),
       ('en', 'pirogi', 'pies'),
       ('en', 'foodOther', 'food other'),
       ('en', 'auto', 'cars'),
       ('en', 'toplivo', 'fuel'),
       ('en', 'avtomoika', 'car wash'),
       ('en', 'himchistka', 'dry cleaning'),
       ('en', 'polirovka', 'polishing'),
       ('en', 'remont', 'repairs'),
       ('en', 'razval', 'wheel alignment'),
       ('en', 'diagnostika', 'suspension diagnostics'),
       ('en', 'autoother', 'car other'),
       ('en', 'salonkrasoty', 'beauty saloons'),
       ('en', 'salonkrasotyother', 'beauty saloons other'),
       ('en', 'udalenievolos', 'depilation'),
       ('en', 'epilyatsiya', 'laser hair removal'),
       ('en', 'manikur', 'manicure'),
       ('en', 'strizhki', 'haircuts'),
       ('en', 'pedikyr', 'pedicure'),
       ('en', 'razvlecheniya', 'entertainment'),
       ('en', 'razvlecheniyaother', 'entertainment other'),
       ('en', 'quest', 'quests'),
       ('en', 'batut', 'trampolines'),
       ('en', 'banya', 'baths'),
       ('en', 'swimmingpool', 'pools'),
       ('en', 'pool', 'billiards'),
       ('en', 'bowling', 'bowling'),
       ('en', 'obuchenie', 'training'),
       ('en', 'obuchenieother', 'training other'),
       ('en', 'music', 'music'),
       ('en', 'dance', 'dance'),
       ('en', 'autoschool', 'autoschool'),
       ('en', 'languages', 'languages');

insert into temp_translation (lang, language_key, value)
values ('ru', 'food', '??????'),
       ('ru', 'sushi', '????????'),
       ('ru', 'pizza', '??????????'),
       ('ru', 'caffee', '???????? ?? ??????????????????'),
       ('ru', 'deserts', '??????????????'),
       ('ru', 'cofe', '????????'),
       ('ru', 'govno', '???????????????????????????? ??????????'),
       ('ru', 'pasta', '??????????'),
       ('ru', 'shaverma', '????????????'),
       ('ru', 'shashlyk', '??????????????'),
       ('ru', 'pirogi', '????????????'),
       ('ru', 'foodOther', '????????????'),
       ('ru', 'auto', '????????'),
       ('ru', 'toplivo', '??????????????'),
       ('ru', 'avtomoika', '??????????????????'),
       ('ru', 'himchistka', '??????????????????'),
       ('ru', 'polirovka', '??????????????????'),
       ('ru', 'remont', '????????????'),
       ('ru', 'razval', '????????????-??????????????????'),
       ('ru', 'diagnostika', '?????????????????????? ????????????????'),
       ('ru', 'autoother', '???????? ????????????'),
       ('ru', 'salonkrasoty', '???????????? ??????????????'),
       ('ru', 'salonkrasotyother', '???????????? ?????????????? ????????????'),
       ('ru', 'udalenievolos', '???????????????? ??????????'),
       ('ru', 'epilyatsiya', '???????????????? ????????????????'),
       ('ru', 'manikur', '??????????????'),
       ('ru', 'strizhki', '??????????????'),
       ('ru', 'pedikyr', '??????????????'),
       ('ru', 'razvlecheniya', '??????????????????????'),
       ('ru', 'razvlecheniyaother', '?????????????????????? ????????????'),
       ('ru', 'quest', '????????????'),
       ('ru', 'batut', '????????????'),
       ('ru', 'banya', '????????'),
       ('ru', 'swimmingpool', '????????????????'),
       ('ru', 'pool', '??????????????'),
       ('ru', 'bowling', '??????????????'),
       ('ru', 'obuchenie', '????????????????'),
       ('ru', 'obuchenieother', '???????????????? ????????????'),
       ('ru', 'music', '????????????'),
       ('ru', 'dance', '??????????'),
       ('ru', 'autoschool', '??????????????????'),
       ('ru', 'languages', '??????????');


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

insert into category (id, key, parent_id) values
    (8, 'elseTireFitting', 1),
    (9, 'food', null),
    (10, 'sushi', 9),
    (11, 'caffee', 9),
    (12, 'deserts', 9),
    (13, 'cofe', 9),
    (14, 'govno', 9),
    (15, 'pasta', 9),
    (16, 'shaverma', 9),
    (17, 'shashlyk', 9),
    (18, 'pirogi', 9),
    (19, 'foodOther', 9),
    (20, 'auto', null),
    (21, 'toplivo', 20),
    (22, 'avtomoika', 20),
    (23, 'himchistka', 20),
    (24, 'polirovka', 20),
    (25, 'remont', 20),
    (26, 'razval', 20),
    (27, 'diagnostika', 20),
    (28, 'autoother', 20),
    (29, 'salonkrasoty', null),
    (30, 'salonkrasotyother', 29),
    (31, 'udalenievolos', 29),
    (32, 'epilyatsiya', 29),
    (33, 'manikur', 29),
    (34, 'strizhki', 29),
    (35, 'pedikyr', 29),
    (36, 'razvlecheniya', null),
    (37, 'razvlecheniyaother', 36),
    (38, 'quest', 36),
    (39, 'batut', 36),
    (40, 'banya', 36),
    (41, 'swimmingpool', 36),
    (42, 'pool', 36),
    (43, 'bowling', 36),
    (44, 'obuchenie', null),
    (45, 'obuchenieother', 44),
    (46, 'music', 44),
    (47, 'dance', 44),
    (48, 'autoschool', 44),
    (49, 'languages', 44);